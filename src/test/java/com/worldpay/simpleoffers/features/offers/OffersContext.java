package com.worldpay.simpleoffers.features.offers;

import com.worldpay.simpleoffers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class OffersContext {

    protected static ConfigurableApplicationContext app;
    protected static HttpResult create_offer_result;
    protected static HttpResult query_offers_result;

    @Autowired
    protected static InMemoryOffersStore store;
    protected static SimpleOffersHttpClient client;
    private static Date tomorrow;


    public static void start_application() throws IOException {
        app = SpringApplication.run(SimpleOffersAppplication.class,"--server.port=" + "8080");
        client = new SimpleOffersHttpClient(8080);
        store = (InMemoryOffersStore)app.getBean("offersStore");
    }

    public static Date tomorrow() {
        if (tomorrow != null) return tomorrow;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        tomorrow = calendar.getTime();
        return tomorrow;
    }

    public static void create_offer() throws IOException {
        create_offer(new OfferBuilder());
    }

    public static void create_offer(OfferBuilder builder) throws IOException {
        create_offer_result = client.createOffer(builder);
    }

    public static void query_offers() throws IOException {
        query_offers_result = client.queryOffers();
    }

    public static void stop_application() {
        app.close();
    }

}
