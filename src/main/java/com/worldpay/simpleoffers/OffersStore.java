package com.worldpay.simpleoffers;

import java.util.UUID;

public interface OffersStore {
    void add(Offer offer);

    void delete(UUID offerId);
}
