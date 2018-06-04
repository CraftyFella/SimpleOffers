package com.worldpay.simpleoffers.features.offers.query;

import com.worldpay.simpleoffers.OfferBuilder;
import com.worldpay.simpleoffers.features.offers.OffersContext;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

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

    private Date in_one_second() {
        if (in_one_second != null) return in_one_second;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 1);
        in_one_second = calendar.getTime();
        return in_one_second;
    }

    @Test
    public void api_returns_410_Gone() {
        assertThat(last_query_offers_result.status(), is(equalTo(410)));
    }
}

