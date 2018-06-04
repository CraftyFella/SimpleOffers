package com.worldpay.simpleoffers.features.offers;

import com.worldpay.simpleoffers.*;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class OffersContext {

    protected ConfigurableApplicationContext app;
    protected HttpResult create_offer_result;
    protected HttpResult query_offers_result;

    @Autowired
    protected InMemoryOffersStore store;
    protected SimpleOffersHttpClient client;
    private Date tomorrow;

    public OffersContext() {
        start_application();
    }

    public void start_application() {
        app = SpringApplication.run(SimpleOffersAppplication.class,"--server.port=" + "8080");
        client = new SimpleOffersHttpClient(8080);
        store = (InMemoryOffersStore)app.getBean("offersStore");
    }

    public Date tomorrow() {
        if (tomorrow != null) return tomorrow;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        tomorrow = calendar.getTime();
        return tomorrow;
    }

    public void create_offer() throws IOException {
        create_offer(new OfferBuilder());
    }

    public void create_offer(OfferBuilder builder) throws IOException {
        create_offer_result = client.createOffer(builder);
    }

    public void query_offers() throws IOException {
        query_offers_result = client.queryOffers();
    }

    @After
    public void stop_application() {
        app.close();
    }

}
