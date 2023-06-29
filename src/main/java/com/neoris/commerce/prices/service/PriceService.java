package com.neoris.commerce.prices.service;

import com.neoris.commerce.prices.controller.PriceNotFoundException;
import com.neoris.commerce.prices.mapper.PriceMapper;
import com.neoris.commerce.prices.model.PriceResponse;
import com.neoris.commerce.prices.model.context.SearchPriceContext;
import com.neoris.commerce.prices.model.entity.Price;
import com.neoris.commerce.prices.repository.PriceRepository;
import com.neoris.commerce.prices.validator.SearchPriceRequestValidator;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@Builder
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    public PriceResponse handle (Map<String, Object> params) {
        return ((Function<Map<String, Object>, SearchPriceContext>)this::buildContext)
                .andThen(this::validateRequest)
                .andThen(this::getPriceByDateTimeAndProductAndBrand)
                .apply(params);
    }

    private SearchPriceContext buildContext(Map<String, Object> params) {
        LocalDateTime applicationDate = (LocalDateTime) params.get("applicationDate");
        Long productId = (Long) params.get("productId");
        Long brandId = (Long) params.get("brandId");

        return SearchPriceContext.builder()
                .applicationDate(applicationDate)
                .productId(productId)
                .brandId(brandId)
                .build();
    }

    private SearchPriceContext validateRequest(SearchPriceContext context) {
        SearchPriceRequestValidator validator = new SearchPriceRequestValidator();
        validator.validate(context);
        return context;
    }
    public PriceResponse getPriceByDateTimeAndProductAndBrand(SearchPriceContext context) {
        Optional<Price> elegibleProduct = priceRepository.findFirstByBrandIdAndProductIdAndDate(context.getBrandId(), context.getProductId(), context.getApplicationDate());
        if (elegibleProduct.isPresent()) {
            return PriceMapper.priceToPriceResponse(elegibleProduct.get());
        }
        throw new PriceNotFoundException("Price not found for the given criteria.");
    }

}

