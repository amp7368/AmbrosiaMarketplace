package com.ambrosia.markets.api.base;

import java.time.Instant;

public class BaseResponse {

    public final Instant date = Instant.now();
    public final String apiVersion = "1.0";

}
