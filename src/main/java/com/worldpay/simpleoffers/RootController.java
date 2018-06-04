package com.worldpay.simpleoffers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController{
    @RequestMapping("/")
    String home() {
        return "Welcome to simple offers :-)";
    }
}
