package com.worldpay.simpleoffers.features.offers.cancel_offer;

import com.worldpay.simpleoffers.features.offers.OffersContext;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class OfferExists extends OffersContext {

    @Before
    public void start() throws IOException {
        create_offer();
        cancel_offer(last_offer_id());
    }

    @Test
    public void api_returns_200_OK() {
        assertThat(last_cancel_offers_result.status(), is(equalTo(200)));
    }

}

