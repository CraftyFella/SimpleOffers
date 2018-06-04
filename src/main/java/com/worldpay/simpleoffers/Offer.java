package com.worldpay.simpleoffers;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Offer {

    public final UUID offerId;
    public final String friendlyDescription;
    public final Amount amount;
    private Date expiryDate;

    public Offer(UUID offerId, String friendlyDescription, Amount amount, Date expiryDate) {
        this.offerId = offerId;
        this.friendlyDescription = friendlyDescription;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }

    public boolean isStillValid() {
        return new Date().before(expiryDate);
    }

    public Date getExpiryDate(){
        return expiryDate;
    }

    public Offer expire() {
        this.expiryDate = one_year_ago();
        return this;
    }

    private Date one_year_ago() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        return calendar.getTime();
    }
}

