package com.worldpay.simpleoffers.cancel;

import com.worldpay.simpleoffers.OffersStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class CancelOfferController {

        private final OffersStore offersStore;

    public CancelOfferController(OffersStore offersStore) {
        this.offersStore = offersStore;
    }

    @DeleteMapping("/offers/{offerId}")
    public ResponseEntity<?> queryOffer(
            @PathVariable UUID offerId) {
        offersStore.expire(offerId);
        return ok().build();
    }

}
