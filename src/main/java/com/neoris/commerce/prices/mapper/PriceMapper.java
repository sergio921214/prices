package com.neoris.commerce.prices.mapper;

import com.neoris.commerce.prices.model.PriceResponse;
import com.neoris.commerce.prices.model.entity.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

    public static PriceResponse priceToPriceResponse(Price price) {
        return PriceResponse.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getPrice())
                .build();
    }
}



