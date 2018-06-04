package com.worldpay.simpleoffers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.UUID;

import static java.lang.String.format;
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

        UUID uuid = UUID.randomUUID();
        store.add(DtoToDomainMapper.toOffer(offer, uuid));

        return ok().header("location", format("/offers/%s", uuid)) .build();
    }

}