package com.worldpay.simpleoffers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class CreateOfferController {

    private final OffersStore store;

    public CreateOfferController(OffersStore store) {
        this.store = store;
    }

    @PostMapping("/offers")
    public ResponseEntity<?> createOffer(
            @Valid @RequestBody CreateOfferRequestDto offer) {

        store.add(DtoToDomainMapper.toOffer(offer));

        return ok().build();
    }

}