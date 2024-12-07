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
     * Find by product id and brand id list.
     *
     * @param productId the product id
     * @param brandId   the brand id
     * @return the list
     */
    public List<PriceDTO> findByProductIdAndBrandId(Long productId, Long brandId) {
        return priceRepository.findByProductIdAndBrandId(productId, brandId).stream()
                .map(getCopier()::toDTO)
                .toList();
    }

    /**
     * Find active price by product id and brand id and date optional.
     *
     * @param productId the product id
     * @param brandId   the brand id
     * @param date      the date
     * @return the optional
     */
    public Optional<PriceDTO> findActivePriceByProductIdAndBrandIdAndDate(Long productId, Long brandId, LocalDateTime date) {
        return priceRepository.findByProductIdAndBrandId(productId, brandId).stream()
                .filter(price -> date.isAfter(price.getStartDate()) && date.isBefore(price.getEndDate()))
                .max(Comparator.comparingInt(PriceEntity::getPriority))
                .map(getCopier()::toDTO);
    }

    /**
     * Find more relevance price between dates optional.
     *
     * @param productId the product id
     * @param brandId   the brand id
     * @param date      the date
     * @return the optional
     */
    public Optional<PriceDTO> findMoreRelevancePriceBetweenDates(Long productId, Long brandId, LocalDateTime date) {
        return priceRepository.findPricesBetweenDates(productId, brandId, date).stream()
                .findFirst()
                .map(getCopier()::toDTO);
    }

    /**
     * Find price between dates list.
     *
     * @param productId the product id
     * @param brandId   the brand id
     * @param date      the date
     * @return the list
     */
    public List<PriceDTO> findPriceBetweenDates(Long productId, Long brandId, LocalDateTime date) {
        return getCopier().toDTOList(priceRepository.findPricesBetweenDates(productId, brandId, date));
    }

}
