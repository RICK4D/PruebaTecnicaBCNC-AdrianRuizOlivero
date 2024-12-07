package com.prueba.tecnica.inditex.controller;

import com.prueba.tecnica.inditex.dto.PriceDTO;
import com.prueba.tecnica.inditex.exception.EntityNotFoundException;
import com.prueba.tecnica.inditex.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    // Obtener un precio por ID
    @GetMapping("/{id}")
    public ResponseEntity<PriceDTO> getPrice(@PathVariable Long id) throws EntityNotFoundException, IllegalArgumentException {
        return priceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener todos los precios
    @GetMapping
    public ResponseEntity<List<PriceDTO>> getAllPrices() {
        return ResponseEntity.ok(priceService.findAll());
    }

    // Crear un nuevo precio
    @PostMapping
    public ResponseEntity<PriceDTO> createPrice(@RequestBody PriceDTO priceDTO) throws EntityNotFoundException, IllegalArgumentException {
        return ResponseEntity.ok(priceService.save(priceDTO));
    }

    // Actualizar un precio existente
    @PutMapping("/{id}")
    public ResponseEntity<PriceDTO> updatePrice(@PathVariable Long id, @RequestBody PriceDTO priceDTO)
            throws EntityNotFoundException, IllegalArgumentException {
        priceDTO.setId(id);
        return ResponseEntity.ok(priceService.update(priceDTO));
    }

    // Eliminar un precio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable Long id) throws EntityNotFoundException {
        priceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Encontrar precios por productId y brandId
    @GetMapping("/search")
    public ResponseEntity<List<PriceDTO>> findByProductIdAndBrandId(@RequestParam Long productId,
                                                                    @RequestParam Long brandId) {
        return ResponseEntity.ok(priceService.findByProductIdAndBrandId(productId, brandId));
    }

    // Encontrar el precio activo en una fecha específica
    @GetMapping("/active")
    public ResponseEntity<PriceDTO> findActivePrice(@RequestParam Long productId, @RequestParam Long brandId,
            @RequestParam String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        return priceService.findActivePriceByProductIdAndBrandIdAndDate(productId, brandId, dateTime)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
