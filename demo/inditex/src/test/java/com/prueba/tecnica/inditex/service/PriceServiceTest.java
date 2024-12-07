package com.prueba.tecnica.inditex.service;

import com.prueba.tecnica.inditex.copier.PriceCopier;
import com.prueba.tecnica.inditex.dto.PriceDTO;
import com.prueba.tecnica.inditex.entity.PriceEntity;
import com.prueba.tecnica.inditex.repository.PriceRepository;
import com.prueba.tecnica.inditex.utils.CheckListExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(CheckListExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceCopier priceCopier;

    @InjectMocks
    private PriceService priceService;

    @Test
    void testFindApplicablePrice_ValidRequest() {
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(1L);
        priceEntity.setPrice(35.50);
        priceEntity.setPriority(0);

        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(1L);
        priceDTO.setPrice(35.50);

        // Simula una respuesta del repository con los datos que le hemos pasado
        when(priceRepository.findPricesBetweenDates(productId, brandId, date))
                .thenReturn(List.of(priceEntity));
        // Comprueba que la conversión se haga correctamente
        when(priceCopier.toDTO(priceEntity)).thenReturn(priceDTO);

        Optional<PriceDTO> result = priceService.findMoreRelevancePriceBetweenDates(productId, brandId, date);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(35.50, result.get().getPrice());
        verify(priceRepository, times(1)).findPricesBetweenDates(productId, brandId, date);
    }

    @Test
    void testFindApplicablePrice_NoResults() {
        Long productId = 99999L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceRepository.findPricesBetweenDates(productId, brandId, date)).thenReturn(Collections.emptyList());

        Optional<PriceDTO> result = priceService.findMoreRelevancePriceBetweenDates(productId, brandId, date);


        assertTrue(result.isEmpty());
        verify(priceRepository, times(1)).findPricesBetweenDates(productId, brandId, date);
    }
}
