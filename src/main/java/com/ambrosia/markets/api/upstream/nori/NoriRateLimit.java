package com.ambrosia.markets.api.upstream.nori;

import com.ambrosia.markets.Ambrosia;
import com.ambrosia.markets.discord.system.log.DiscordLog;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.BandwidthBuilder;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.local.LocalBucket;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Supplier;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

public class NoriRateLimit {

    private static final Bucket itemAnalysisRateLimit = noriRateLimit();
    private static final OkHttpClient client = new OkHttpClient();
    private static final Duration TIMEOUT = Duration.ofSeconds(15);

    private static LocalBucket noriRateLimit() {
        Bandwidth globalLimit = BandwidthBuilder.builder().capacity(60)
            .refillIntervally(180, Duration.ofMinutes(1))
            .build();
        return Bucket.builder()
            .addLimit(globalLimit)
            .build();
    }

    public static CompletableFuture<Response> itemAnalysis(Supplier<Request> requestFn) {
        return itemAnalysisRateLimit.asScheduler()
            .tryConsume(1, TIMEOUT, executor())
            .thenApply(success -> {
                if (!success) {
                    String err = "Timeout when waiting for additional item analysis rate limit tokens.";
                    throw new RuntimeException(err);
                }
                return call(requestFn.get());
            });
    }

    @NotNull
    public static Response call(Request request) throws CompletionException {
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            DiscordLog.errorSystem("Error with nori call", e);
            throw new CompletionException(e);
        }
    }

    private static ScheduledExecutorService executor() {
        return Ambrosia.get().executor();
    }
}
