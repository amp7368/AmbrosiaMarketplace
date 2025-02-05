package com.ambrosia.markets.api.upstream;

public class UpstreamException extends Exception {

    private final int code;

    public UpstreamException(int code) {
        this.code = code;
    }
}
