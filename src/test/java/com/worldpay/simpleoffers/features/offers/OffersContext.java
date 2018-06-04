package com.worldpay.simpleoffers.features.offers;

import com.worldpay.simpleoffers.HttpResult;
import com.worldpay.simpleoffers.InMemoryOffersStore;
import com.worldpay.simpleoffers.SimpleOffersAppplication;
import com.worldpay.simpleoffers.SimpleOffersHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public abstract class OffersContext {

    protected static ConfigurableApplicationContext app;
    protected static HttpResult create_offer_result;

    @Autowired
    protected static InMemoryOffersStore store;
    protected static Date tomorrow;
    protected static SimpleOffersHttpClient client;

    public static void start_application() throws IOException {
        app = SpringApplication.run(SimpleOffersAppplication.class,"--server.port=" + "8080");
        client = new SimpleOffersHttpClient(8080);
        store = (InMemoryOffersStore)app.getBean("offersStore");
    }

    protected static Date tomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    public static void stop_application() {
        app.close();
    }

}
