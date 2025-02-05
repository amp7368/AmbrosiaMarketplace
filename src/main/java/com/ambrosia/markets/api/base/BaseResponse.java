package com.ambrosia.markets.api.base;

import java.time.Instant;

public class BaseResponse {

    public Instant date = Instant.now();
    public String apiVersion = "1.0";

}
