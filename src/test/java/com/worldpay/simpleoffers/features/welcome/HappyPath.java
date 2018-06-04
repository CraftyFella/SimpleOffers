package com.worldpay.simpleoffers.features.welcome;

import com.worldpay.simpleoffers.HttpResult;
import com.worldpay.simpleoffers.SimpleOffersAppplication;
import com.worldpay.simpleoffers.SimpleOffersHttpClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HappyPath {
    private static ConfigurableApplicationContext app;
    private static HttpResult result;

    @BeforeClass
    public static void startApp() throws IOException {
        app = SpringApplication.run(SimpleOffersAppplication.class,"--server.port=" + "8080");
        result = new SimpleOffersHttpClient(8080).welcome();
    }

    @AfterClass
    public static void stop() {
        app.close();
    }

    @Test
    public void api_returns_200_OK() {
        assertThat(result.status(), is(equalTo(200)));
    }

    @Test
    public void api_returns_welcome_message() {
        assertThat(result.body(), is(equalTo("Welcome to simple offers :-)")));
    }

}
