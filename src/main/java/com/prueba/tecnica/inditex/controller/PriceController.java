package com.prueba.tecnica.inditex.controller;

import com.prueba.tecnica.inditex.dto.PriceDTO;
import com.prueba.tecnica.inditex.exception.EntityNotFoundException;
import com.prueba.tecnica.inditex.service.PriceService;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
     * Gets price.
     *
     * @param id the id
     * @return the price
     * @throws EntityNotFoundException  the entity not found exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    @GetMapping("/{id}")
    public ResponseEntity<PriceDTO> getPrice(@PathVariable Long id) throws IllegalArgumentException {
        return priceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Gets all prices.
     *
     * @return the all prices
     */
    @GetMapping
    public ResponseEntity<List<PriceDTO>> getAllPrices() {
        return ResponseEntity.ok(priceService.findAll());
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
