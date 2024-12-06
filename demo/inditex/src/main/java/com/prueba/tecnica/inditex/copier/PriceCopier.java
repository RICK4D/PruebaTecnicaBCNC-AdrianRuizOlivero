package com.prueba.tecnica.inditex.copier;

import com.prueba.tecnica.inditex.dto.PriceDTO;
import com.prueba.tecnica.inditex.entity.BrandEntity;
import com.prueba.tecnica.inditex.entity.PriceEntity;
import com.prueba.tecnica.inditex.entity.ProductEntity;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

public class PriceCopier extends AbstractCopier<PriceEntity, PriceDTO> {

    @Override
    public PriceDTO toDTO(@NonNull PriceEntity entity) {
        return PriceDTO.builder()
                .id(entity.getId())
                .brandId(entity.getBrand().getId())
                .productId(entity.getProduct().getId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .priceList(entity.getPriceList())
                .priority(entity.getPriority())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .build();
    }

    @Override
    public PriceEntity toEntity(@NonNull PriceDTO dto) {
        BrandEntity brand = BrandEntity.builder().id(dto.getBrandId()).build();
        ProductEntity product = ProductEntity.builder().id(dto.getProductId()).build();

        return PriceEntity.builder()
                .id(dto.getId())
                .brand(brand)
                .product(product)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .priceList(dto.getPriceList())
                .priority(dto.getPriority())
                .price(dto.getPrice())
                .currency(dto.getCurrency())
                .build();
    }
}
