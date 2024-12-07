package com.prueba.tecnica.inditex.controller;

import com.prueba.tecnica.inditex.dto.PriceDTO;
import com.prueba.tecnica.inditex.service.PriceService;
import com.prueba.tecnica.inditex.utils.CheckListExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(CheckListExtension.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PriceService priceService;

    private static final String BASE_URL = "/api/prices/applicable";

    @Test
    void testGetApplicablePrice_ValidRequest1() throws Exception {
        // Arrange
        Long productId = 1L;
        Long brandId = 1L;
        String date = "2020-06-14T10:00:00";

        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(1L); // Ajustado a lo que se espera validar
        priceDTO.setBrandId(brandId);
        priceDTO.setProductId(productId);
        priceDTO.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        priceDTO.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceDTO.setPrice(35.5);
        priceDTO.setCurrency("EUR");
        priceDTO.setPriority(0);
        priceDTO.setPriceList(1);

        System.out.println("🟢 Starting: Test 1: petición a las 10:00 del día 14 del producto 1 para la brand 1 (ZARA)");

        // Act & Assert
        mockMvc.perform(get(BASE_URL)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .param("date", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(priceDTO.getId()))
                .andExpect(jsonPath("$.brandId").value(priceDTO.getBrandId()))
                .andExpect(jsonPath("$.productId").value(priceDTO.getProductId()))
                .andExpect(jsonPath("$.price").value(priceDTO.getPrice()))
                .andExpect(jsonPath("$.endDate").value(priceDTO.getEndDate().toString()))
                .andExpect(jsonPath("$.priority").value(priceDTO.getPriority()))
                .andExpect(jsonPath("$.currency").value(priceDTO.getCurrency()))
                .andExpect(jsonPath("$.priceList").value(priceDTO.getPriceList()));

        System.out.println("Resultado de la búsqueda: " + priceDTO);
    }

    @Test
    void testGetApplicablePrice_ValidRequest2() throws Exception {
        // Arrange
        Long productId = 1L;
        Long brandId = 1L;
        String date = "2020-06-14T16:00:00";

        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(2L); // Ajustado a lo que se espera validar
        priceDTO.setBrandId(brandId);
        priceDTO.setProductId(productId);
        priceDTO.setStartDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0));
        priceDTO.setEndDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0));
        priceDTO.setPrice(25.45);
        priceDTO.setCurrency("EUR");
        priceDTO.setPriority(1);
        priceDTO.setPriceList(2);

        System.out.println("🟢 Starting: Test 2: petición a las 16:00 del día 14 del producto 1 para la brand 1 (ZARA)");

        // Act & Assert
        mockMvc.perform(get(BASE_URL)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .param("date", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(priceDTO.getId()))
                .andExpect(jsonPath("$.brandId").value(priceDTO.getBrandId()))
                .andExpect(jsonPath("$.productId").value(priceDTO.getProductId()))
                .andExpect(jsonPath("$.price").value(priceDTO.getPrice()))
                .andExpect(jsonPath("$.priority").value(priceDTO.getPriority()))
                .andExpect(jsonPath("$.currency").value(priceDTO.getCurrency()))
                .andExpect(jsonPath("$.priceList").value(priceDTO.getPriceList()));

        System.out.println("Resultado de la búsqueda: " + priceDTO);
    }


    @Test
    void testGetApplicablePrice_ValidRequest3() throws Exception {
        // Arrange
        Long productId = 1L;
        Long brandId = 1L;
        String date = "2020-06-14T21:00:00";

        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(1L); // Ajustado a lo que se espera validar
        priceDTO.setBrandId(brandId);
        priceDTO.setProductId(productId);
        priceDTO.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        priceDTO.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceDTO.setPrice(35.5);
        priceDTO.setCurrency("EUR");
        priceDTO.setPriority(0);
        priceDTO.setPriceList(1);

        System.out.println("🟢 Starting: Test 3: petición a las 21:00 del día 14 del producto 1 para la brand 1 (ZARA)");

        // Act & Assert
        mockMvc.perform(get(BASE_URL)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .param("date", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(priceDTO.getId()))
                .andExpect(jsonPath("$.brandId").value(priceDTO.getBrandId()))
                .andExpect(jsonPath("$.productId").value(priceDTO.getProductId()))
                .andExpect(jsonPath("$.price").value(priceDTO.getPrice()))
                .andExpect(jsonPath("$.endDate").value(priceDTO.getEndDate().toString()))
                .andExpect(jsonPath("$.priority").value(priceDTO.getPriority()))
                .andExpect(jsonPath("$.currency").value(priceDTO.getCurrency()))
                .andExpect(jsonPath("$.priceList").value(priceDTO.getPriceList()));

        System.out.println("Resultado de la búsqueda: " + priceDTO);
    }


    @Test
    void testGetApplicablePrice_ValidRequest4() throws Exception {
        // Arrange
        Long productId = 1L;
        Long brandId = 1L;
        String date = "2020-06-15T10:00:00";

        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(3L); // Ajustado a lo que se espera validar
        priceDTO.setBrandId(brandId);
        priceDTO.setProductId(productId);
        priceDTO.setStartDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0));
        priceDTO.setEndDate(LocalDateTime.of(2020, 12, 31, 11, 0, 0));
        priceDTO.setPrice(30.5);
        priceDTO.setCurrency("EUR");
        priceDTO.setPriority(1);
        priceDTO.setPriceList(3);

        System.out.println("🟢 Starting: Test 4: petición a las 10:00 del día 15 del producto 1 para la brand 1 (ZARA)");

        // Act & Assert
        mockMvc.perform(get(BASE_URL)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .param("date", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(priceDTO.getId()))
                .andExpect(jsonPath("$.brandId").value(priceDTO.getBrandId()))
                .andExpect(jsonPath("$.productId").value(priceDTO.getProductId()))
                .andExpect(jsonPath("$.price").value(priceDTO.getPrice()))
                .andExpect(jsonPath("$.priority").value(priceDTO.getPriority()))
                .andExpect(jsonPath("$.currency").value(priceDTO.getCurrency()))
                .andExpect(jsonPath("$.priceList").value(priceDTO.getPriceList()));

        System.out.println("Resultado de la búsqueda: " + priceDTO);
    }


    @Test
    void testGetApplicablePrice_ValidRequest5() throws Exception {
        // Arrange
        Long productId = 1L;
        Long brandId = 1L;
        String date = "2020-06-16T21:00:00";

        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(4L); // Ajustado a lo que se espera validar
        priceDTO.setBrandId(brandId);
        priceDTO.setProductId(productId);
        priceDTO.setStartDate(LocalDateTime.of(2020, 6, 16, 0, 0, 0));
        priceDTO.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceDTO.setPrice(38.95);
        priceDTO.setCurrency("EUR");
        priceDTO.setPriority(1);
        priceDTO.setPriceList(4);

        System.out.println("🟢 Starting: Test 5: petición a las 21:00 del día 16 del producto 1 para la brand 1 (ZARA)");

        // Act & Assert
        mockMvc.perform(get(BASE_URL)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .param("date", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(priceDTO.getId()))
                .andExpect(jsonPath("$.brandId").value(priceDTO.getBrandId()))
                .andExpect(jsonPath("$.productId").value(priceDTO.getProductId()))
                .andExpect(jsonPath("$.price").value(priceDTO.getPrice()))
                .andExpect(jsonPath("$.endDate").value(priceDTO.getEndDate().toString()))
                .andExpect(jsonPath("$.priority").value(priceDTO.getPriority()))
                .andExpect(jsonPath("$.currency").value(priceDTO.getCurrency()))
                .andExpect(jsonPath("$.priceList").value(priceDTO.getPriceList()));

        System.out.println("Resultado de la búsqueda: " + priceDTO);
    }


    @Test
    void testGetApplicablePrice_InvalidDateFormat() throws Exception {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        String date = "invalid-date-format";

        // Act & Assert
        mockMvc.perform(get(BASE_URL)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .param("date", date))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetApplicablePrice_MissingParameters() throws Exception {
        // Act & Assert
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isBadRequest());
    }
}
