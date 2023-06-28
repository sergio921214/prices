package com.neoris.commerce.prices.unit;

import com.neoris.commerce.prices.model.PriceResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceResponseDataBuilder {
    private Long productId;
    private Long brandId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;

    public PriceResponseDataBuilder withProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public PriceResponseDataBuilder withBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public PriceResponseDataBuilder withPriceList(Integer priceList) {
        this.priceList = priceList;
        return this;
    }

    public PriceResponseDataBuilder withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public PriceResponseDataBuilder withEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public PriceResponseDataBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public PriceResponse build() {
        return new PriceResponse(productId, brandId, priceList, startDate, endDate, price);
    }
}

