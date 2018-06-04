package com.worldpay.simpleoffers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class CreateOfferController {

    private final OffersStore store;

    public CreateOfferController(OffersStore store) {
        this.store = store;
    }

    @PostMapping("/offers")
    public ResponseEntity<?> createOffer(
            @RequestBody CreateOfferRequestDto offer) {

        store.add(DtoToDomainMapper.toOffer(offer));

        return ok().build();
    }

}