package com.worldpay.simpleoffers.features.create_offer;

import com.worldpay.simpleoffers.InMemoryOffersStore;
import com.worldpay.simpleoffers.OfferBuilder;
import com.worldpay.simpleoffers.SimpleOffersAppplication;
import com.worldpay.simpleoffers.SimpleOffersHttpClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static com.worldpay.simpleoffers.Amount.GBP;
import static com.worldpay.simpleoffers.InMemoryOffersStore.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsEqual.equalTo;

public class InvalidRequests {

    private static ConfigurableApplicationContext app;
    
    @Autowired
    private static InMemoryOffersStore store;
    private static SimpleOffersHttpClient client;

    @BeforeClass
    public static void startApp() {
        app = SpringApplication.run(SimpleOffersAppplication.class,"--server.port=" + "8080");
        store = (InMemoryOffersStore)app.getBean("offersStore");
        
        client = new SimpleOffersHttpClient(8080);
    }

    @AfterClass
    public static void stop() {
        app.close();
    }

    @Test
    public void missing_description_returns_400() throws IOException {
        OfferBuilder request = new OfferBuilder().withDesc(null);
        assertThat(client.createOffer(request).status(), equalTo(400));
    }

    @Test
    public void missing_currency_returns_400() throws IOException {
        OfferBuilder request = new OfferBuilder().withCurrency(null);
        assertThat(client.createOffer(request).status(), equalTo(400));
    }

    @Test
    public void invalid_currency_returns_400() throws IOException {
        OfferBuilder request = new OfferBuilder().withCurrency("INVALID");
        assertThat(client.createOffer(request).status(), equalTo(400));
    }

    @Test
    public void missing_amount_returns_400() throws IOException {
        OfferBuilder request = new OfferBuilder().withAmount(null);
        assertThat(client.createOffer(request).status(), equalTo(400));
    }

    @Test
    public void date_in_past_returns_400() throws IOException {
        OfferBuilder request = new OfferBuilder().withExpiry(yesterday());
        assertThat(client.createOffer(request).status(), equalTo(400));
    }

    private static Date yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }
}

