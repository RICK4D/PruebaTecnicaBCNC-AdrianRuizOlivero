package com.prueba.tecnica.inditex.service;


import com.prueba.tecnica.inditex.copier.AbstractCopier;
import com.prueba.tecnica.inditex.copier.PriceCopier;
import com.prueba.tecnica.inditex.dto.PriceDTO;
import com.prueba.tecnica.inditex.entity.PriceEntity;
import com.prueba.tecnica.inditex.repository.IRepository;
import com.prueba.tecnica.inditex.repository.PriceRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService extends AbstractService<PriceEntity, PriceDTO> {

    private final PriceRepository priceRepository;
    private final PriceCopier priceCopier;

    public PriceService(PriceRepository priceRepository, PriceCopier priceCopier) {
        this.priceRepository = priceRepository;
        this.priceCopier = priceCopier;
    }

    @Override
    protected IRepository<PriceEntity, Long> getRepository() {
        return this.priceRepository;
    }

    @Override
    protected AbstractCopier<PriceEntity, PriceDTO> getCopier() {
        return this.priceCopier;
    }

    // Método adicional: Encontrar precios por productId y brandId
    public List<PriceDTO> findByProductIdAndBrandId(Long productId, Long brandId) {
        return priceRepository.findByProductIdAndBrandId(productId, brandId).stream()
                .map(getCopier()::toDTO)
                .toList();
    }

    // Método adicional: Encontrar el precio activo en una fecha específica
    public Optional<PriceDTO> findActivePriceByProductIdAndBrandIdAndDate(Long productId, Long brandId, LocalDateTime date) {
        return priceRepository.findByProductIdAndBrandId(productId, brandId).stream()
                .filter(price -> date.isAfter(price.getStartDate()) && date.isBefore(price.getEndDate()))
                .max(Comparator.comparingInt(PriceEntity::getPriority))
                .map(getCopier()::toDTO);
    }

    public Optional<PriceDTO> findMoreRelevancePriceBetweenDates(Long productId, Long brandId, LocalDateTime date) {
        return priceRepository.findPricesBetweenDates(productId, brandId, date).stream()
                .findFirst()
                .map(getCopier()::toDTO);
    }

    public List<PriceDTO> findPriceBetweenDates(Long productId, Long brandId, LocalDateTime date) {
        return getCopier().toDTOList(priceRepository.findPricesBetweenDates(productId, brandId, date));
    }

}
