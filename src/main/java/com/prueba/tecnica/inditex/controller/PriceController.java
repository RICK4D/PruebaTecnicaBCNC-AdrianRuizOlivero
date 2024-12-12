package com.prueba.tecnica.inditex.controller;

import com.prueba.tecnica.inditex.dto.PriceDTO;
import com.prueba.tecnica.inditex.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Price controller.
 */
@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    /**
     * Instantiates a new Price controller.
     *
     * @param priceService the price service
     */
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }


    /**
     * Gets applicable price.
     *
     * @param productId the product id
     * @param brandId   the brand id
     * @param date      the date
     * @return the applicable price
     */
    @GetMapping("/applicable")
    public ResponseEntity<PriceDTO> getApplicablePrice(@RequestParam Long productId, @RequestParam Long brandId,
            @RequestParam String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return priceService.findMoreRelevantPriceBetweenDates(productId, brandId, dateTime)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
