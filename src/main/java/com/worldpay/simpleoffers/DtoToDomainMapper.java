package com.worldpay.simpleoffers;

import com.worldpay.simpleoffers.create.OfferRequestDto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.UUID;

public class DtoToDomainMapper {

    public static Offer toOffer(OfferRequestDto offer, UUID offerId) {

        return new Offer(offerId, offer.getFriendlyDescription(), toAmount(offer.getAmount()), parseDate(offer.getExpiryDate()));
    }

    public static Amount toAmount(AmountDto amount) {
        return new Amount(new BigDecimal(amount.getValue()), parseCurrency(amount));
    }

    private static Currency parseCurrency(AmountDto amount) {
        return Currency.getInstance(amount.getCurrency());
    }

    private static ZonedDateTime parseDate(String dateString) {
        ZonedDateTime date = ZonedDateTime.parse(dateString);
        if (date.isBefore(ZonedDateTime.now())) {
            throw new IllegalArgumentException("expiry date must be in the future");
        }
        return date;
    }
}

