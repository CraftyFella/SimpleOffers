package com.worldpay.simpleoffers;

import com.worldpay.simpleoffers.query.OfferByOfferId;
import com.worldpay.simpleoffers.query.OfferResponseDto;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import java.util.*;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;

public class InMemoryOffersStore implements OffersStore, OfferByOfferId {
    public List<Offer> offers = new ArrayList<>();

    @Override
    public void add(Offer offer) {
        offers.add(offer);
    }

    @Override
    public void delete(UUID offerId) {
        offers.removeIf(o -> offerId.equals(o.offerId));
    }

    @Override
    public Optional<OfferResponseDto> get(UUID offerId) {
        return offers.stream().filter(o -> offerId.equals(o.offerId)).findFirst()
                .map(DomainToDtoMapper::toOffer);
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
