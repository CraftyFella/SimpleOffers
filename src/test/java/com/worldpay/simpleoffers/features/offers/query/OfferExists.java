package com.worldpay.simpleoffers.features.offers.query;

import com.worldpay.simpleoffers.features.offers.OffersContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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

    @Test
    public void api_returns_body_with_amount() {
        assertThat(last_query_offers_result.body(), hasJsonPath("$.amount.value", equalTo("12.34")));
    }

    @Test
    public void api_returns_body_with_currency() {
        assertThat(last_query_offers_result.body(), hasJsonPath("$.amount.currency", equalTo("GBP")));
    }

    @Test
    public void api_returns_body_with_expiry() {
        assertThat(last_query_offers_result.body(), hasJsonPath("$.expiryDate", equalTo(tomorrow().toString())));
    }

}

