package com.worldpay.simpleoffers;

import java.math.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.UUID;

public class DtoToDomainMapper {

    public static Offer toOffer(CreateOfferRequestDto offer, UUID uuid) {

        return new Offer(offer.getFriendlyDescription(), toAmount(offer.getAmount()), parseDate(offer.getExpiryDate()));
    }

    public static Amount toAmount(AmountDto amount) {
        return new Amount(new BigDecimal(amount.getValue()), parseCurrency(amount));
    }

    private static Currency parseCurrency(AmountDto amount) {
        return Currency.getInstance(amount.getCurrency());
    }

    private static Date parseDate(String dateString){
        try {
            SimpleDateFormat format =
                    new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

            Date date = format.parse(dateString);

            if (date.before(new Date())){
                throw new IllegalArgumentException("Date must be in the future");
            }
            return date;
        }
        catch(ParseException pe) {
            throw new IllegalArgumentException(pe);
        }
    }
}
