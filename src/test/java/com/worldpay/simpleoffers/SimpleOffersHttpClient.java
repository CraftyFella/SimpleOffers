package com.worldpay.simpleoffers;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

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

    public HttpResult createOffer(OfferBuilder builder) throws IOException {

        Request request = post("/offers", builder.build().toString());
        Response response = client.newCall(request).execute();
        return new OkHttpResult(response);
    }

    public HttpResult queryOffer(UUID offerId) throws IOException {
        Request request = get("/offers/" + offerId);
        Response response = client.newCall(request).execute();
        return new OkHttpResult(response);
    }

    public HttpResult cancelOffer(UUID offerId) throws IOException {
        Request request = delete("/offers/" + offerId);
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

    private Request delete(String resource) {
        String url = baseUrl + resource;

        return new Request
                .Builder()
                .delete()
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


