package com.ambrosia.markets.api.upstream;

import com.ambrosia.markets.Ambrosia;
import com.ambrosia.markets.api.system.AppStatusCode;
import com.ambrosia.markets.discord.system.log.DiscordLog;
import io.github.bucket4j.BandwidthBuilder;
import io.github.bucket4j.Bucket;
import io.javalin.http.HttpResponseException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

public class DiscordRateLimit {

    private static final ScheduledExecutorService EXECUTOR = Executors.newScheduledThreadPool(5);
    private static final int RATE_LIMIT = 429;
    private static final OkHttpClient globalClient = new OkHttpClient();
    private static final Bucket globalRateLimit = Bucket.builder()
        .addLimit(
            BandwidthBuilder.builder().capacity(600)
                .refillIntervally(600, Duration.ofMinutes(10))
                .build())
        .build();
    protected List<DiscordRateLimit> rateLimits = new ArrayList<>();

    public static CompletableFuture<Response> makeTokenRequest(Request request) {
        return rateLimitDiscordRequest(request, globalClient);
    }

    public static CompletableFuture<Response> rateLimitDiscordRequest(Request request, OkHttpClient client) {
        return globalRateLimit.asScheduler()
            .tryConsume(1, Duration.ofSeconds(30), EXECUTOR)
            .handleAsync((consumeSuccess, err) -> {
                RateLimitHelper.handleConsumeError(consumeSuccess, err);
                return RateLimitHelper.discordRequest(request, client);
            }, Ambrosia.get().executor());
    }

    private static class RateLimitHelper {


        private static void handleConsumeError(Boolean consumeSuccess, Throwable err) throws CompletionException {
            String msg = null;
            if (err != null) {
                msg = "Hit error when rate limiting???";
            } else if (!consumeSuccess) {
                msg = "Could not fulfill rate limit in time!";
                err = new HttpResponseException(AppStatusCode.RATE_LIMIT, msg);
            }
            if (msg != null) throw new CompletionException(msg, err);
        }

        private static @NotNull Response discordRequest(Request request, OkHttpClient client) throws CompletionException {
            try {
                Response response = client.newCall(request).execute();
                readDiscordHeaders(response);
                return response;
            } catch (IOException e) {
                DiscordLog.errorSystem("Error with discord call", e);
                throw new CompletionException(e);
            }
        }

        private static void readDiscordHeaders(Response response) {
            if (response.code() == DiscordRateLimit.RATE_LIMIT)
                globalRateLimit.tryConsumeAsMuchAsPossible();
            String resetAt = response.header("X-RateLimit-Reset");
            String resetTo = response.header("X-RateLimit-Limit");

            int tokensRemaining = -1;
            try {
                String header = response.header("X-RateLimit-Remaining");
                if (header != null) tokensRemaining = Integer.parseInt(header);
            } catch (NumberFormatException ignored) {
            }
            long availableTokens = globalRateLimit.getAvailableTokens();
            if (tokensRemaining >= 0 && tokensRemaining < availableTokens) {
                globalRateLimit.tryConsumeAsMuchAsPossible(availableTokens - tokensRemaining);
            }
        }
    }
}
