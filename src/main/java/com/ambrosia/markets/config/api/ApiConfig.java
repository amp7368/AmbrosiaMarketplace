package com.ambrosia.markets.config.api;

import io.javalin.config.JavalinConfig;
import io.javalin.plugin.bundled.CorsPluginConfig.CorsRule;

public class ApiConfig {

    protected final int port = 3001;
    public boolean cors = false;

    public void commonConfig(JavalinConfig config) {
        config.router.treatMultipleSlashesAsSingleSlash = true;
        config.router.ignoreTrailingSlashes = true;
        config.showJavalinBanner = false;
        if (this.cors)
            config.bundledPlugins.enableCors((cors -> cors.addRule(CorsRule::anyHost)));
    }

    public int getPort() {
        return this.port;
    }
}
