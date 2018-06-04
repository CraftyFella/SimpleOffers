package com.worldpay.simpleoffers;

import org.json.JSONObject;

import java.time.ZonedDateTime;
import java.util.Date;

public class OfferBuilder {

    private String amountValue = "12.34";
    private String amountCurrency = "GBP";
    private String desc = "Desc";
    private ZonedDateTime expiry = ZonedDateTime.now().plusDays(1);

    public JSONObject build() {

        JSONObject amountJson = new JSONObject()
                .put("value", amountValue)
                .put("currency", amountCurrency);

        return new JSONObject()
                .put("amount", amountJson)
                .put("friendlyDescription", desc)
                .put("expiryDate", expiry.toString());

    }

    public OfferBuilder withDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public OfferBuilder withAmount(String amountValue) {
        this.amountValue = amountValue;
        return this;
    }

    public OfferBuilder withCurrency(String amountCurrency) {
        this.amountCurrency = amountCurrency;
        return this;
    }

    public OfferBuilder withExpiry(ZonedDateTime expiry) {
        this.expiry = expiry;
        return this;
    }
}
