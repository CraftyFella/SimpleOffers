package com.worldpay.simpleoffers.query;

import com.worldpay.simpleoffers.AmountDto;

public class OfferResponseDto {

    private final String offerId;
    private final String friendlyDescription;
    private final AmountDto amount;
    private final String expiryDate;

    @java.beans.ConstructorProperties({"offerId", "friendlyDescription", "amount", "expiryDate"})
    public OfferResponseDto(String offerId, String friendlyDescription, AmountDto amount, String expiryDate) {
        this.offerId = offerId;
        this.friendlyDescription = friendlyDescription;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }

    public String getOfferId() {
        return offerId;
    }

    public String getFriendlyDescription() {
        return friendlyDescription;
    }

    public AmountDto getAmount() {
        return amount;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

}
