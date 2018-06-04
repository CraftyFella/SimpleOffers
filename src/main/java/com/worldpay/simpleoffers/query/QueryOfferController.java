package com.worldpay.simpleoffers.query;

import com.worldpay.simpleoffers.Offer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.worldpay.simpleoffers.DomainToDtoMapper.toOffer;
import static org.springframework.http.ResponseEntity.*;

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
                .map(QueryOfferController::toHttpResult)
                .orElse(notFound().build());
    }

    private static ResponseEntity<? extends Object> toHttpResult(Offer offer) {
        if (offer.isStillValid()) {
            return ok().body(toOffer(offer));
        } else {
            return status(410).build();
        }
    }
}
