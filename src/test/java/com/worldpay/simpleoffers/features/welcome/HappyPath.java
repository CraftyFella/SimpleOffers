package com.worldpay.simpleoffers.features.welcome;

import com.worldpay.simpleoffers.HttpResult;
import com.worldpay.simpleoffers.SimpleOffersAppplication;
import com.worldpay.simpleoffers.SimpleOffersHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SimpleOffersAppplication.class})
public class HappyPath {
    @LocalServerPort
    private int port;
    private HttpResult result;

    @Before
    public void beforeEach() throws IOException {
        result = new SimpleOffersHttpClient(port).welcome();
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
