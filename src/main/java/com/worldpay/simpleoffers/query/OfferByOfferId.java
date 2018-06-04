package com.worldpay.simpleoffers.query;

import com.worldpay.simpleoffers.query.OfferResponseDto;

import java.util.Optional;
import java.util.UUID;

public interface OfferByOfferId {
    Optional<OfferResponseDto> get(UUID offerId);
}
