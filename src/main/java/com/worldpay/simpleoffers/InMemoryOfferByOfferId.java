package com.worldpay.simpleoffers;

import com.worldpay.simpleoffers.query.OfferByOfferId;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.util.*;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;


public class InMemoryOfferByOfferId implements OfferByOfferId {

    public Map<UUID, Offer> offers;

    public InMemoryOfferByOfferId(Map<UUID, Offer> offers) {
        this.offers = offers;
    }

    @Override
    public Optional<Offer> get(UUID offerId) {
        return Optional.ofNullable(offers.getOrDefault(offerId, null));
    }
}

