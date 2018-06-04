package com.worldpay.simpleoffers;

public interface HttpResult {
    int status();
    String header(String key);
    String body();
}
