package com.worldpay.simpleoffers;

import okhttp3.Response;

import java.io.IOException;

public class OkHttpResult implements HttpResult {

    private final String body;
    private final int status;

    public OkHttpResult(Response response) throws IOException {

        this.body = response.body().string();
        this.status = response.code();
    }

    @Override
    public int status() {
        return this.status;
    }

    @Override
    public String body() {
        return this.body;
    }
}
