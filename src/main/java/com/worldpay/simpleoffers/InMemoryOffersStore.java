package com.worldpay.simpleoffers;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;

public class InMemoryOffersStore implements OffersStore {

    public Map<UUID, Offer> offers;

    public InMemoryOffersStore(Map<UUID, Offer> offers) {
        this.offers = offers;
    }

    @Override
    public void add(Offer offer) {
        offers.putIfAbsent(offer.offerId, offer);
    }

    @Override
    public void expire(UUID offerId) {
        offers.computeIfPresent(offerId, (uuid, offer) -> offer.expire());
    }

    public static Matcher<InMemoryOffersStore> contains(Matcher<Offer> matcher) {
        return new FeatureMatcher<InMemoryOffersStore, Iterable<Offer>>(org.hamcrest.Matchers.contains(matcher),
                "In Memory Offers Store contains ", "offer") {
            protected Iterable<Offer> featureValueOf(InMemoryOffersStore actual) {
                return actual.offers.values();
            }
        };
    }

    public static Matcher<Offer> withOfferId(UUID offerId) {

        return new FeatureMatcher<Offer, UUID>(equalTo(offerId),
                "offer with offer id of ", "offerId") {
            protected UUID featureValueOf(Offer actual) {
                return actual.offerId;
            }
        };
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

    public static Matcher<Offer> withExpiryDate(ZonedDateTime date) {
        return new FeatureMatcher<Offer, String>(equalTo(date.toString()),
                "offer with expiry date of ", "date") {
            protected String  featureValueOf(Offer actual) {
                return actual.getExpiryDate().toString();
            }
        };
    }

    public static Matcher<Offer> anOffer(Matcher<Offer>... matchers){
        return allOf(matchers);
    }


}
