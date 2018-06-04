package com.worldpay.simpleoffers;

import java.math.BigDecimal;
import java.util.Currency;

public class Amount {
    public final BigDecimal value;
    public final Currency currency;

    @java.beans.ConstructorProperties({"value", "currency"})
    public Amount(final BigDecimal value, final Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public static final Currency GBP = Currency.getInstance("GBP");
}
