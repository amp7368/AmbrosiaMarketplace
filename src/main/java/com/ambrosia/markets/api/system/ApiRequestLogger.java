package com.ambrosia.markets.api.system;

import com.ambrosia.markets.api.ApiModule;
import io.javalin.http.Context;
import io.javalin.http.RequestLogger;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class ApiRequestLogger implements RequestLogger {

    @Override
    public void handle(@NotNull Context context, @NotNull Float time) throws Exception {
        Logger logger = ApiModule.get().logger();
        String message = "Status %d @ %s -- %.1fms".formatted(context.statusCode(), context.path(), time);
        if (context.statusCode() < 300) {
            logger.info(message);
            return;
        }
        if (context.statusCode() < 500) {
            logger.warn(message);
            return;
        }
        logger.error("{}{}", message, context.result());
    }

}
