package com.worldpay.simpleoffers.features.offers.create;

import com.worldpay.simpleoffers.OfferBuilder;
import com.worldpay.simpleoffers.features.offers.OffersContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static com.worldpay.simpleoffers.Amount.GBP;
import static com.worldpay.simpleoffers.InMemoryOffersStore.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsEqual.equalTo;

public class HappyPath extends OffersContext {

    @BeforeClass
    public static void start() throws IOException {
        start_application();
        create_offer(new OfferBuilder()
                        .withDesc("Friendly desc")
                        .withAmount("10.50")
                        .withCurrency("GBP")
                        .withExpiry(tomorrow()));
    }

    @AfterClass
    public static void stop() {
        stop_application();
    }

    @Test
    public void api_returns_200_OK() {
        assertThat(create_offer_result.status(), is(equalTo(200)));
    }

    @Test
    public void api_returns_location_for_created_offer() {
        assertThat(create_offer_result.header("location"), startsWith("/offers/"));
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
        assertThat(store.offers, contains(anOffer(withExpiryDate(tomorrow()))));
    }

}

