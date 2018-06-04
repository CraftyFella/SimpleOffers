package com.worldpay.simpleoffers;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class SimpleOffersHttpClient {

    private final OkHttpClient client;
    private final String baseUrl;
    private static final MediaType JSON = MediaType.parse("application/json");

    public SimpleOffersHttpClient(int port) {

        this.baseUrl = String.format("http://localhost:%s", port);
        client = new OkHttpClient.Builder()
                .build();
    }

    public HttpResult welcome() throws IOException {
        Request request = get("/");
        Response response = client.newCall(request).execute();
        return new OkHttpResult(response);
    }

    public HttpResult createOffer(String friendlyDescription) throws IOException {
        JSONObject body = new JSONObject().put("friendly-description", friendlyDescription);
        Request request = post("/offers", body.toString());
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

    private Request post(String resource, String body) {
        String url = baseUrl + resource;

        return new Request
                .Builder()
                .post(RequestBody.create(JSON, body))
                .url(url).build();
    }
}


