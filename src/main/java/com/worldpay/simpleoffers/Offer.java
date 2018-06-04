package com.worldpay.simpleoffers;

import java.util.Date;

public class Offer {

    public final String friendlyDescription;
    public final Amount amount;
    public final Date expiryDate;

    public Offer(String friendlyDescription, Amount amount, Date expiryDate) {

        this.friendlyDescription = friendlyDescription;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }
}

