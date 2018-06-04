package com.worldpay.simpleoffers.create;

import com.worldpay.simpleoffers.DtoToDomainMapper;
import com.worldpay.simpleoffers.OffersStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @Valid @RequestBody OfferRequestDto offer) {

        UUID offerId = UUID.randomUUID();
        store.add(DtoToDomainMapper.toOffer(offer, offerId));

        return ok().header("location", format("/offers/%s", offerId)) .build();
    }

}

