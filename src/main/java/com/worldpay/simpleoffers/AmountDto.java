package com.worldpay.simpleoffers;

import java.math.BigDecimal;
import java.util.Currency;

public class AmountDto {

    public final String value;

    public final String currency;

    @java.beans.ConstructorProperties({"value", "currency"})
    public AmountDto(String value, String currency) {
        this.value = value;
        this.currency = currency;
    }

}

