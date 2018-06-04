package com.worldpay.simpleoffers;

import java.util.Date;
import java.util.UUID;

public class Offer {

    public final UUID offerId;
    public final String friendlyDescription;
    public final Amount amount;
    public final Date expiryDate;

    public Offer(UUID offerId, String friendlyDescription, Amount amount, Date expiryDate) {
        this.offerId = offerId;
        this.friendlyDescription = friendlyDescription;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }
}

