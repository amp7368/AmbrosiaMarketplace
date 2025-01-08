package com.ambrosia.markets.api.system;

import apple.utilities.gson.adapter.time.InstantGsonSerializing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.ResponseBody;

public class ApiGson {

    public static Gson gson() {
        GsonBuilder gson = new GsonBuilder();
        InstantGsonSerializing.registerGson(gson);
        return gson.create();
    }

    public static <T> T fromJson(ResponseBody body, Class<T> type) {
        return gson().fromJson(body.charStream(), type);
    }
}
