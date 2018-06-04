package com.worldpay.simpleoffers.features.create_offer;

import com.worldpay.simpleoffers.*;
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

public class HappyPath {

    private static ConfigurableApplicationContext app;
    private static HttpResult result;

    @Autowired
    private static InMemoryOffersStore store;
    private static Date tomorrow;

    @BeforeClass
    public static void startApp() throws IOException {
        app = SpringApplication.run(SimpleOffersAppplication.class,"--server.port=" + "8080");
        store = (InMemoryOffersStore)app.getBean("offersStore");

        tomorrow = tomorrow();

        result = new SimpleOffersHttpClient(8080)
                .createOffer(
                        new OfferBuilder()
                                .withDesc("Friendly desc")
                                .withAmount("10.50")
                                .withCurrency("GBP")
                                .withExpiry(tomorrow));
    }

    private static Date tomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    @AfterClass
    public static void stop() {
        app.close();
    }

    @Test
    public void api_returns_200_OK() {
        assertThat(result.status(), is(equalTo(200)));
    }

    @Test
    public void db_contains_offer_with_matching_description() {
        assertThat(store.offers, contains(anOffer(withFriendlyDescription("Friendly desc"))));
    }

    @Test
    public void db_contains_offer_with_matching_amount() {
        assertThat(store.offers, contains(anOffer(
                withValue("10.50"),
                withCurrency(GBP))));
    }

    @Test
    public void db_contains_offer_with_matching_expiry() {
        assertThat(store.offers, contains(anOffer(withExpiryDate(tomorrow))));
    }

}

