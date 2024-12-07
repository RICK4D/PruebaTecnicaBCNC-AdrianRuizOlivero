package com.prueba.tecnica.inditex.repository;

import com.prueba.tecnica.inditex.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends IRepository<PriceEntity, Long> {
    // Agregar método específico para la lógica de Price si es necesario
    List<PriceEntity> findByProductIdAndBrandId(Long productId, Long brandId);

    @Query("SELECT p FROM PriceEntity p WHERE p.product.id = :productId AND p.brand.id = :brandId " +
            "AND :date BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC")
    List<PriceEntity> findPricesBetweenDates(@Param("productId") Long productId, @Param("brandId") Long brandId,
                                           @Param("date") LocalDateTime date);
}
