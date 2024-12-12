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

/**
 * The type Price service.
 */
@Service
public class PriceService extends AbstractService<PriceEntity, PriceDTO> {

    private final PriceRepository priceRepository;
    private final PriceCopier priceCopier;

    /**
     * Instantiates a new Price service.
     *
     * @param priceRepository the price repository
     * @param priceCopier     the price copier
     */
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


    /**
     * Find more relevant price between dates optional.
     *
     * @param productId the product id
     * @param brandId   the brand id
     * @param date      the date
     * @return the optional
     */
    public Optional<PriceDTO> findMoreRelevantPriceBetweenDates(Long productId, Long brandId, LocalDateTime date) {
        return priceRepository.findMoreRelevantPriceBetweenDates(productId, brandId, date)
                .map(getCopier()::toDTO);
    }

}
