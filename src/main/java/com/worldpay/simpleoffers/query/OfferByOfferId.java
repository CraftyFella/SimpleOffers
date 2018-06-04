package com.worldpay.simpleoffers.query;

import com.worldpay.simpleoffers.Offer;

import java.util.Optional;
import java.util.UUID;

public interface OfferByOfferId {
    Optional<Offer> get(UUID offerId);
}
