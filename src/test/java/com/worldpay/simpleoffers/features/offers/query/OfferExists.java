package com.worldpay.simpleoffers.features.offers.query;

import com.worldpay.simpleoffers.features.offers.OffersContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static com.worldpay.simpleoffers.Amount.GBP;
import static com.worldpay.simpleoffers.InMemoryOffersStore.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsEqual.equalTo;

public class OfferExists extends OffersContext {

    @Before
    public void start() throws IOException {
        create_offer();
        query_offer(last_offer_id());
    }


    @After
    public void stop() {
        stop_application();
    }

    @Test
    public void api_returns_200_OK() {
        assertThat(last_query_offers_result.status(), is(equalTo(200)));
    }

    @Test
    public void api_returns_body_with_description() {
        assertThat(last_query_offers_result.body(), hasJsonPath("$.friendlyDescription", equalTo("Desc")));
    }

}

