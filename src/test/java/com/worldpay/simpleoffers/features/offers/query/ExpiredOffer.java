package com.worldpay.simpleoffers.features.offers.query;

import com.worldpay.simpleoffers.OfferBuilder;
import com.worldpay.simpleoffers.features.offers.OffersContext;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ExpiredOffer extends OffersContext {

    private Date in_one_second;

    @Before
    public void start() throws IOException, InterruptedException {
        create_offer(new OfferBuilder().withExpiry(in_one_second()));
        simulate_just_over_1_second_elapsed();
        query_offer(last_offer_id());
    }

    private void simulate_just_over_1_second_elapsed() throws InterruptedException {
        Thread.sleep(1100L); // TODO: Replace with mocked clock if I had more time
    }

    private ZonedDateTime in_one_second() {
        return ZonedDateTime.now().plusSeconds(1);
    }

    @Test
    public void api_returns_410_Gone() {
        assertThat(last_query_offers_result.status(), is(equalTo(410)));
    }
}

