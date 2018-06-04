package com.worldpay.simpleoffers.features.offers.create;

import com.worldpay.simpleoffers.OfferBuilder;
import com.worldpay.simpleoffers.features.offers.OffersContext;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class InvalidRequests extends OffersContext {

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

