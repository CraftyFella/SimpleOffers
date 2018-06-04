package com.worldpay.simpleoffers;

import javax.validation.constraints.NotNull;

public class AmountDto {

    @NotNull
    private final String value;

    @NotNull
    private final String currency;

    @java.beans.ConstructorProperties({"value", "currency"})
    public AmountDto(String value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }


}

