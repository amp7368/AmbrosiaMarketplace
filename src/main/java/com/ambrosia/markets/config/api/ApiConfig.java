package com.ambrosia.markets.config.api;

import io.javalin.config.JavalinConfig;
import io.javalin.plugin.bundled.CorsPluginConfig.CorsRule;

public class ApiConfig {

    public boolean cors = false;
    protected int port = 3001;
    protected String domain = "marketplace.dreamcogs.com/api";

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

    public String getDomain() {
        return this.domain;
    }
}
