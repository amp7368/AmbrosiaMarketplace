package com.ambrosia.markets.api;

import apple.lib.modules.AppleModule;
import com.ambrosia.markets.api.auth.AuthController;
import com.ambrosia.markets.api.system.ApiRequestLogger;
import com.ambrosia.markets.api.system.ExceptionHandlers;
import com.ambrosia.markets.api.v1.controller.marketplace.items.MarketplaceItemsController;
import com.ambrosia.markets.api.v1.controller.user.me.ProfileController;
import com.ambrosia.markets.api.v1.controller.user.me.items.InventoryController;
import com.ambrosia.markets.api.v1.controller.user.me.items.auctions.ItemAuctionsController;
import com.ambrosia.markets.api.v1.controller.users.UsersController;
import com.ambrosia.markets.api.v1.controller.users.items.UsersItemsController;
import com.ambrosia.markets.config.AmbrosiaConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import java.text.DateFormat;
import java.util.TimeZone;

public class ApiModule extends AppleModule {

    private static ApiModule instance;

    public ApiModule() {
        instance = this;
    }

    public static ApiModule get() {
        return instance;
    }

    private void registerControllers(Javalin app) {
        app.post("/api/v1/token", new AuthController()::authorize);

        // public marketplace
        app.get("/api/v1/marketplace/items", new MarketplaceItemsController()::listItems);

        // public users
        app.get("/api/v1/users/{user}/items", new UsersItemsController()::listItems);
        app.get("/api/v1/users/{user}", new UsersController()::getProfile);

        // private user data
        ProfileController profile = new ProfileController();
        app.get("/api/v1/user/me", profile::getProfile);
        InventoryController inventory = new InventoryController();
        app.post("/api/v1/user/me/items", inventory::createItem);
        app.get("/api/v1/user/me/items", inventory::listItems);
        app.patch("/api/v1/user/me/items/{item}/auction", new ItemAuctionsController()::markForAuction);

    }

//    private void registerPermissions() {
//        Arrays.stream(DefaultAuthPermission.values()).forEach(DefaultAuthPermission::get);
//    }

    private int getPort() {
        return AmbrosiaConfig.getApi().getPort();
    }

    @Override
    public String getName() {
        return "Api";
    }

    @Override
    public void onEnable() {
        Javalin app = Javalin.create(cfg -> {
            AmbrosiaConfig.getApi().commonConfig(cfg);
            cfg.requestLogger.http(new ApiRequestLogger());

            JavalinJackson jsonMapper = new JavalinJackson()
                .updateMapper(mapper -> {
                    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

                    DateFormat dateFormat = DateFormat.getDateTimeInstance();
                    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    mapper.setDateFormat(dateFormat);
                });
            cfg.jsonMapper(jsonMapper);
        });
//        app.beforeMatched(new ApiSecurity()::manage);

        app.beforeMatched(ctx -> {
            String body = ctx.body().replaceAll("\\s+", "");
            logger().info("{} - BODY: {}", ctx.url(), body);
        });

        registerControllers(app);
        ExceptionHandlers.registerExceptions(app);

        app.start(getPort());
    }
}
