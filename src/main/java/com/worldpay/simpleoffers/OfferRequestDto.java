package com.worldpay.simpleoffers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class OfferRequestDto {

    @NotNull
    private final String friendlyDescription;
    @Valid
    private final AmountDto amount;
    @NotNull
    private final String expiryDate;

    @java.beans.ConstructorProperties({"friendlyDescription", "amount", "expiryDate"})
    public OfferRequestDto(String friendlyDescription, AmountDto amount, String expiryDate) {
        this.friendlyDescription = friendlyDescription;
        this.amount = amount;
        this.expiryDate = expiryDate;
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
