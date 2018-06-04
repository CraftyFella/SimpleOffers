package com.worldpay.simpleoffers;

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
public class RootControllerTest {

    @LocalServerPort
    private int port;

    private SimpleOffersHttpClient client;

    @Before
    public void setup() {
        client = new SimpleOffersHttpClient(port);
    }

    @Test
    public void returns_welcome_message() throws IOException {
        HttpResult result = client.root();
        assertThat(result.body(), is(equalTo("Welcome to simple offers :-)")));
    }

    @Test
    public void returns_ok() throws IOException {
        HttpResult result = client.root();
        assertThat(result.status(), is(equalTo(200)));
    }

}

