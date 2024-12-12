package com.prueba.tecnica.inditex.repository;

import com.prueba.tecnica.inditex.entity.PriceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The interface Price repository.
 */
@Repository
public interface PriceRepository extends IRepository<PriceEntity, Long> {
     /**
     * Find more relevant price between dates list.
     *
     * @param productId the product id
     * @param brandId   the brand id
     * @param date      the date
     * @return the list
     */
    @Query("SELECT p FROM PriceEntity p WHERE p.product.id = :productId AND p.brand.id = :brandId " +
            "AND :date BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC LIMIT 1")
    Optional<PriceEntity> findMoreRelevantPriceBetweenDates(@Param("productId") Long productId, @Param("brandId") Long brandId,
                                                 @Param("date") LocalDateTime date);
}
