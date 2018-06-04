package com.worldpay.simpleoffers;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;

public class InMemoryOffersStore implements OffersStore {
    public List<Offer> offers = new ArrayList<>();

    @Override
    public void add(Offer offer) {
        offers.add(offer);
    }

    public static Matcher<Offer> withFriendlyDescription(String friendlyDesc) {

        return new FeatureMatcher<Offer, String>(equalTo(friendlyDesc),
                "offer with friendly description of ", "friendlyDescription") {
            protected String featureValueOf(Offer actual) {
                return actual.friendlyDescription;
            }
        };
    }

    public static Matcher<Offer> withValue(String amount) {
        return new FeatureMatcher<Offer, String>(equalTo(amount),
                "offer with amount value of ", "amount") {
            protected String featureValueOf(Offer actual) {
                return actual.amount.value.toString();
            }
        };
    }

    public static Matcher<Offer> withCurrency(Currency currency) {
        return new FeatureMatcher<Offer, Currency>(equalTo(currency),
                "offer with amount currency of ", "currency") {
            protected Currency featureValueOf(Offer actual) {
                return actual.amount.currency;
            }
        };
    }

    public static Matcher<Offer> withExpiryDate(Date date) {
        return new FeatureMatcher<Offer, String>(equalTo(date.toString()),
                "offer with expiry date of ", "date") {
            protected String  featureValueOf(Offer actual) {
                return actual.expiryDate.toString();
            }
        };
    }

    public static Matcher<Offer> anOffer(Matcher<Offer>... matchers){
        return allOf(matchers);
    }

}
