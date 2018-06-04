package com.worldpay.simpleoffers;

import com.worldpay.simpleoffers.query.OfferResponseDto;

public class DomainToDtoMapper {

    public static OfferResponseDto toOffer(Offer offer) {
        return new OfferResponseDto(offer.offerId.toString(), offer.friendlyDescription, toAmount(offer.amount), offer.expiryDate.toString());
    }

    public static AmountDto toAmount(Amount amount) {
        return new AmountDto(amount.value.toString(), amount.currency.toString());
    }

}
