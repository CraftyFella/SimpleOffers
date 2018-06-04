package com.worldpay.simpleoffers.features.offers.cancel_offer;

import com.worldpay.simpleoffers.features.offers.OffersContext;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static com.worldpay.simpleoffers.InMemoryOffersStore.anOffer;
import static com.worldpay.simpleoffers.InMemoryOffersStore.withFriendlyDescription;
import static com.worldpay.simpleoffers.InMemoryOffersStore.withOfferId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

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

