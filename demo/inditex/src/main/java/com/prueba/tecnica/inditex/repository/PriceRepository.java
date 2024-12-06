package com.prueba.tecnica.inditex.repository;

import com.prueba.tecnica.inditex.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends IRepository<PriceEntity, Long> {
    // Agregar método específico para la lógica de Price si es necesario
    List<PriceEntity> findByProductIdAndBrandId(Long productId, Long brandId);
}
