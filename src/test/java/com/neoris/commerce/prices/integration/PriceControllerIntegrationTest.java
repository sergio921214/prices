package com.neoris.commerce.prices.integration;

import com.neoris.commerce.prices.model.PriceResponse;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@FlywayTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPrice_duringStandardDateTimePrice_thenReturnsPriceResponse() throws Exception {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        PriceResponse expectedResponse = PriceResponse.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .price(new BigDecimal("35.5"))
                .build();

        // Act
        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", comparesEqualTo(expectedResponse.getPrice().doubleValue())))
                .andExpect(jsonPath("$.productId", comparesEqualTo(expectedResponse.getProductId().intValue())))
                .andExpect(jsonPath("$.brandId", comparesEqualTo(expectedResponse.getBrandId().intValue())))
                .andExpect(jsonPath("$.priceList", comparesEqualTo(expectedResponse.getPriceList())))
                .andExpect(jsonPath("$.startDate", comparesEqualTo("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.endDate", comparesEqualTo("2020-12-31T23:59:59")));
    }

    @Test
    void getPrice_duringDateTimeWith30PercentDiscount_thenReturnsPriceResponse() throws Exception {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        PriceResponse expectedResponse = PriceResponse.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(2)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
                .price(new BigDecimal("25.45"))
                .build();

        // Act
        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", comparesEqualTo(expectedResponse.getPrice().doubleValue())))
                .andExpect(jsonPath("$.productId", comparesEqualTo(expectedResponse.getProductId().intValue())))
                .andExpect(jsonPath("$.brandId", comparesEqualTo(expectedResponse.getBrandId().intValue())))
                .andExpect(jsonPath("$.priceList", comparesEqualTo(expectedResponse.getPriceList())))
                .andExpect(jsonPath("$.startDate", comparesEqualTo("2020-06-14T15:00:00")))
                .andExpect(jsonPath("$.endDate", comparesEqualTo("2020-06-14T18:30:00")));
    }

    @Test
    void getPrice_duringDateTimeWithoutDiscount_thenReturnsPriceResponse() throws Exception {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        PriceResponse expectedResponse = PriceResponse.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .price(new BigDecimal("35.5"))
                .build();

        // Act
        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", comparesEqualTo(expectedResponse.getPrice().doubleValue())))
                .andExpect(jsonPath("$.productId", comparesEqualTo(expectedResponse.getProductId().intValue())))
                .andExpect(jsonPath("$.brandId", comparesEqualTo(expectedResponse.getBrandId().intValue())))
                .andExpect(jsonPath("$.priceList", comparesEqualTo(expectedResponse.getPriceList())))
                .andExpect(jsonPath("$.startDate", comparesEqualTo("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.endDate", comparesEqualTo("2020-12-31T23:59:59")));
    }

    @Test
    void getPrice_duringDateTimeWith15PercentDiscount_thenReturnsPriceResponse() throws Exception {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        PriceResponse expectedResponse = PriceResponse.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(3)
                .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                .price(new BigDecimal("30.5"))
                .build();

        // Act
        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", comparesEqualTo(expectedResponse.getPrice().doubleValue())))
                .andExpect(jsonPath("$.productId", comparesEqualTo(expectedResponse.getProductId().intValue())))
                .andExpect(jsonPath("$.brandId", comparesEqualTo(expectedResponse.getBrandId().intValue())))
                .andExpect(jsonPath("$.priceList", comparesEqualTo(expectedResponse.getPriceList())))
                .andExpect(jsonPath("$.startDate", comparesEqualTo("2020-06-15T00:00:00")))
                .andExpect(jsonPath("$.endDate", comparesEqualTo("2020-06-15T11:00:00")));
    }

    @Test
    void getPrice_duringDateTimeWith10PercentSurcharge_thenReturnsPriceResponse() throws Exception {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        PriceResponse expectedResponse = PriceResponse.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(4)
                .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .price(new BigDecimal("38.95"))
                .build();

        // Act
        mockMvc.perform(get("/prices")
                        .param("applicationDate", applicationDate.toString())
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", comparesEqualTo(expectedResponse.getPrice().doubleValue())))
                .andExpect(jsonPath("$.productId", comparesEqualTo(expectedResponse.getProductId().intValue())))
                .andExpect(jsonPath("$.brandId", comparesEqualTo(expectedResponse.getBrandId().intValue())))
                .andExpect(jsonPath("$.priceList", comparesEqualTo(expectedResponse.getPriceList())))
                .andExpect(jsonPath("$.startDate", comparesEqualTo("2020-06-15T16:00:00")))
                .andExpect(jsonPath("$.endDate", comparesEqualTo("2020-12-31T23:59:59")));
    }

}
