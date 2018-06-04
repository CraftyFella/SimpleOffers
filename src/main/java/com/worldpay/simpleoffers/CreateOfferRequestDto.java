package com.worldpay.simpleoffers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CreateOfferRequestDto {

    @NotNull
    private final String friendlyDescription;
    @Valid
    private final AmountDto amount;
    @NotNull
    private final String expiryDate;

    @java.beans.ConstructorProperties({"friendlyDescription", "amount", "expiryDate"})
    public CreateOfferRequestDto(String friendlyDescription, AmountDto amount, String expiryDate) {
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
