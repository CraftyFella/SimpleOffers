package com.worldpay.simpleoffers;

import java.util.Optional;
import java.util.UUID;

public interface OfferByOfferId {
    Optional<OfferResponseDto> get(UUID offerId);
}
