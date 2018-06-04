package com.worldpay.simpleoffers;

public class CreateOfferRequestDto {

    public final String friendlyDescription;
    public final AmountDto amount;
    public final String expiryDate;

    @java.beans.ConstructorProperties({"friendlyDescription", "amount", "expiryDate"})
    public CreateOfferRequestDto(String friendlyDescription, AmountDto amount, String expiryDate) {
        this.friendlyDescription = friendlyDescription;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }

}
