package com.worldpay.simpleoffers;

import okhttp3.Response;

import java.io.IOException;

public class OkHttpResult implements HttpResult {

    private final String body;
    private final int status;
    private final Response response;

    public OkHttpResult(Response response) throws IOException {

        this.response = response;
        this.body = response.body().string();
        this.status = response.code();
    }

    @Override
    public int status() {
        return this.status;
    }

    @Override
    public String header(String key) {
        return response.header(key);
    }

    @Override
    public String body() {
        return this.body;
    }
}
