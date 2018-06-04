package com.worldpay.simpleoffers;

import java.math.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

public class DtoToDomainMapper {

    public static Offer toOffer(CreateOfferRequestDto offer) {

        return new Offer(offer.friendlyDescription, toAmount(offer.amount), parseDate(offer.expiryDate));
    }

    public static Amount toAmount(AmountDto amount) {
        return new Amount(new BigDecimal(amount.value), Currency.getInstance(amount.currency));
    }

    private static Date parseDate(String dateString){
        try {
            SimpleDateFormat format =
                    new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            return format.parse(dateString);
        }
        catch(ParseException pe) {
            throw new IllegalArgumentException(pe);
        }
    }
}
