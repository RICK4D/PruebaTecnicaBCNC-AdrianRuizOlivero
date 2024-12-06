package com.prueba.tecnica.inditex.service;


import com.prueba.tecnica.inditex.copier.AbstractCopier;
import com.prueba.tecnica.inditex.copier.PriceCopier;
import com.prueba.tecnica.inditex.dto.PriceDTO;
import com.prueba.tecnica.inditex.entity.PriceEntity;
import com.prueba.tecnica.inditex.repository.IRepository;
import com.prueba.tecnica.inditex.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService extends AbstractService<PriceEntity, PriceDTO> {

    private final PriceRepository priceRepository;
    private final PriceCopier priceCopier;

    public PriceService(PriceRepository priceRepository, PriceCopier priceCopier) {
        this.priceRepository = priceRepository;
        this.priceCopier = priceCopier;
    }

    // Método adicional: Encontrar precios por productId y brandId
    public List<PriceDTO> findByProductIdAndBrandId(Long productId, Long brandId) {
        return priceRepository.findByProductIdAndBrandId(productId, brandId).stream()
                .map(getCopier()::toDTO)
                .toList();
    }

    @Override
    protected IRepository<PriceEntity, Long> getRepository() {
        return this.priceRepository;
    }

    @Override
    protected AbstractCopier<PriceEntity, PriceDTO> getCopier() {
        return this.priceCopier;
    }
}
