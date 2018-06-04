package com.worldpay.simpleoffers;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Offer {

    public final UUID offerId;
    public final String friendlyDescription;
    public final Amount amount;
    private ZonedDateTime expiryDate;

    public Offer(UUID offerId, String friendlyDescription, Amount amount, ZonedDateTime expiryDate) {
        this.offerId = offerId;
        this.friendlyDescription = friendlyDescription;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }

    public boolean isStillValid() {
        return ZonedDateTime.now().isBefore(expiryDate);
    }

    public ZonedDateTime getExpiryDate(){
        return expiryDate;
    }

    public Offer expire() {
        this.expiryDate = one_year_ago();
        return this;
    }

    private ZonedDateTime one_year_ago() {
        return ZonedDateTime.now().minusYears(1);
    }
}

