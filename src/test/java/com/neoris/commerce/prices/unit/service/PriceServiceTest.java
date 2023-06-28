package com.neoris.commerce.prices.unit.service;

import com.neoris.commerce.prices.controller.PriceNotFoundException;
import com.neoris.commerce.prices.mapper.PriceMapper;
import com.neoris.commerce.prices.model.PriceResponse;
import com.neoris.commerce.prices.model.context.SearchPriceContext;
import com.neoris.commerce.prices.model.entity.Price;
import com.neoris.commerce.prices.repository.PriceRepository;
import com.neoris.commerce.prices.service.PriceService;
import com.neoris.commerce.prices.unit.PriceEntityDataBuilder;
import com.neoris.commerce.prices.unit.PriceResponseDataBuilder;
import com.neoris.commerce.prices.validator.SearchPriceRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceServiceTest {
    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;

    @Mock
    private SearchPriceRequestValidator validator;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testHandle_PriceNotFound_ThrowsPriceNotFoundException() {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        Map<String, Object> params = new HashMap<>();
        params.put("applicationDate", applicationDate);
        params.put("productId", productId);
        params.put("brandId", brandId);

        when(priceRepository.findFirstByBrandIdAndProductIdAndDate(brandId, productId, applicationDate))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PriceNotFoundException.class, () -> priceService.handle(params));
        verify(priceRepository).findFirstByBrandIdAndProductIdAndDate(brandId, productId, applicationDate);
    }

    @Test
    void testGetPriceByDateTimeAndProductAndBrand_PriceFound_ReturnsPriceResponse() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        LocalDateTime endDate = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        Price price = new PriceEntityDataBuilder()
                .withId(1L)
                .withBrandId(1L)
                .withStartDate(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))
                .withEndDate(LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999))
                .withPriceList(1)
                .withProductId(35455L)
                .withPriority(0)
                .withPrice(new BigDecimal("35.50"))
                .withCurrency("EUR")
                .build();

        PriceResponse expectedResponse = new PriceResponseDataBuilder()
                .withProductId(35455L)
                .withBrandId(1L)
                .withPriceList(1)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withPrice(new BigDecimal("35.50"))
                .build();

        SearchPriceContext context = SearchPriceContext.builder()
                .applicationDate(applicationDate)
                .productId(productId)
                .brandId(brandId)
                .build();

        when(priceRepository.findFirstByBrandIdAndProductIdAndDate(brandId, productId, applicationDate))
                .thenReturn(Optional.of(price));

        // Act
        PriceResponse result = priceService.getPriceByDateTimeAndProductAndBrand(context);

        // Assert
        assertEquals(expectedResponse, result);
    }

    @Test
    void testGetPriceByDateTimeAndProductAndBrand_PriceNotFound_ThrowsPriceNotFoundException() {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        when(priceRepository.findFirstByBrandIdAndProductIdAndDate(brandId, productId, applicationDate))
                .thenReturn(Optional.empty());

        SearchPriceContext context = SearchPriceContext.builder()
                .applicationDate(applicationDate)
                .productId(productId)
                .brandId(brandId)
                .build();

        // Act & Assert
        assertThrows(PriceNotFoundException.class, () -> priceService.getPriceByDateTimeAndProductAndBrand(context));
        verify(priceRepository).findFirstByBrandIdAndProductIdAndDate(brandId, productId, applicationDate);
    }
}
