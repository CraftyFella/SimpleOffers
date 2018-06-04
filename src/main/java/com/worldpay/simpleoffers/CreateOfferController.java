package com.worldpay.simpleoffers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class CreateOfferController {

    @PostMapping("/offers")
    public ResponseEntity<?> createOffer(
            @RequestBody CreateOfferRequestDto offer) {
        return ok().build();
    }

    public static class CreateOfferRequestDto {

    }
}
