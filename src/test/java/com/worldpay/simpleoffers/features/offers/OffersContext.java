package com.worldpay.simpleoffers.features.offers;

import com.worldpay.simpleoffers.*;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class OffersContext {

    protected ConfigurableApplicationContext app;
    protected HttpResult last_create_offer_result;
    protected HttpResult last_query_offers_result;

    @Autowired
    protected InMemoryOffersStore store;
    protected SimpleOffersHttpClient client;
    private Date tomorrow;

    public OffersContext() {
        start_application();
    }

    private void start_application() {
        app = SpringApplication.run(SimpleOffersAppplication.class,"--server.port=" + "8080");
        client = new SimpleOffersHttpClient(8080);
        store = (InMemoryOffersStore)app.getBean("offersStore");
    }

    protected Date tomorrow() {
        if (tomorrow != null) return tomorrow;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        tomorrow = calendar.getTime();
        return tomorrow;
    }

    protected void create_offer() throws IOException {
        create_offer(new OfferBuilder().withExpiry(tomorrow()));
    }

    protected void create_offer(OfferBuilder builder) throws IOException {
        last_create_offer_result = client.createOffer(builder);
    }

    protected void query_offer(UUID offerId) throws IOException {
        last_query_offers_result = client.queryOffer(offerId);
    }

    protected UUID last_offer_id() {
        String offerIdFromLocation = last_create_offer_result.header("location").substring("/offer/".length() + 1);
        return UUID.fromString(offerIdFromLocation);
    }

    @After
    public void stop_application() {
        app.close();
    }

}
