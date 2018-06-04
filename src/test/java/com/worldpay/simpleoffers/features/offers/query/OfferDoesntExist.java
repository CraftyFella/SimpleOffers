package com.worldpay.simpleoffers.features.offers.query;

import com.worldpay.simpleoffers.features.offers.OffersContext;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class OfferDoesntExist extends OffersContext {

    @Before
    public void start() throws IOException {
        query_offer(UUID.randomUUID());
    }

    @Test
    public void api_returns_404() {
        assertThat(last_query_offers_result.status(), is(equalTo(404)));
    }
}

