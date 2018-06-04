package com.worldpay.simpleoffers;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class SimpleOffersHttpClient {

    private final OkHttpClient client;
    private final String baseUrl;

    public SimpleOffersHttpClient(int port) {

        this.baseUrl = String.format("http://localhost:%s", port);
        client = new OkHttpClient.Builder()
                .build();
    }

    public HttpResult root() throws IOException {
        Request request = get("/");
        Response response = client.newCall(request).execute();
        return new OkHttpResult(response);
    }

    private Request get(String resource) {
        String url = baseUrl + resource;

        return new Request
                .Builder()
                .get()
                .url(url).build();
    }
}


