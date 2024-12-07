package com.prueba.tecnica.inditex.controller;

import com.prueba.tecnica.inditex.dto.PriceDTO;
import com.prueba.tecnica.inditex.exception.EntityNotFoundException;
import com.prueba.tecnica.inditex.service.PriceService;
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
    public ResponseEntity<PriceDTO> getPrice(@PathVariable Long id) throws EntityNotFoundException, IllegalArgumentException {
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
     * Create price response entity.
     *
     * @param priceDTO the price dto
     * @return the response entity
     * @throws EntityNotFoundException  the entity not found exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    @PostMapping
    public ResponseEntity<PriceDTO> createPrice(@RequestBody PriceDTO priceDTO) throws EntityNotFoundException, IllegalArgumentException {
        return ResponseEntity.ok(priceService.save(priceDTO));
    }

    /**
     * Update price response entity.
     *
     * @param id       the id
     * @param priceDTO the price dto
     * @return the response entity
     * @throws EntityNotFoundException  the entity not found exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    @PutMapping("/{id}")
    public ResponseEntity<PriceDTO> updatePrice(@PathVariable Long id, @RequestBody PriceDTO priceDTO)
            throws EntityNotFoundException, IllegalArgumentException {
        priceDTO.setId(id);
        return ResponseEntity.ok(priceService.update(priceDTO));
    }

    /**
     * Delete price response entity.
     *
     * @param id the id
     * @return the response entity
     * @throws EntityNotFoundException the entity not found exception
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable Long id) throws EntityNotFoundException {
        priceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Find by product id and brand id response entity.
     *
     * @param productId the product id
     * @param brandId   the brand id
     * @return the response entity
     */
    @GetMapping("/search")
    public ResponseEntity<List<PriceDTO>> findByProductIdAndBrandId(@RequestParam Long productId,
                                                                    @RequestParam Long brandId) {
        return ResponseEntity.ok(priceService.findByProductIdAndBrandId(productId, brandId));
    }

    /**
     * Find active price response entity.
     *
     * @param productId the product id
     * @param brandId   the brand id
     * @param date      the date
     * @return the response entity
     */
// Encontrar el precio activo en una fecha específica
    @GetMapping("/active")
    public ResponseEntity<PriceDTO> findActivePrice(@RequestParam Long productId, @RequestParam Long brandId,
            @RequestParam String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        return priceService.findActivePriceByProductIdAndBrandIdAndDate(productId, brandId, dateTime)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
    public ResponseEntity<PriceDTO> getApplicablePrice(
            @RequestParam Long productId,
            @RequestParam Long brandId,
            @RequestParam String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return priceService.findMoreRelevancePriceBetweenDates(productId, brandId, dateTime)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
