package com.worldpay.simpleoffers.query;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class QueryOfferController {

    private final OfferByOfferId offersQuery;

    public QueryOfferController(OfferByOfferId query) {

        this.offersQuery = query;
    }

    @GetMapping("/offers/{offerId}")
    public ResponseEntity<?> queryOffer(
            @PathVariable UUID offerId) {

        return offersQuery.get(offerId)
                .map(ok()::body)
                .orElse(notFound().build());
    }

}
