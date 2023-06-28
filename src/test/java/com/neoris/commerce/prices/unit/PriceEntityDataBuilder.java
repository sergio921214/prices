package com.neoris.commerce.prices.unit;

import com.neoris.commerce.prices.model.entity.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceEntityDataBuilder {

    private Long id;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;

    public PriceEntityDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PriceEntityDataBuilder withBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public PriceEntityDataBuilder withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public PriceEntityDataBuilder withEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public PriceEntityDataBuilder withPriceList(Integer priceList) {
        this.priceList = priceList;
        return this;
    }

    public PriceEntityDataBuilder withProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public PriceEntityDataBuilder withPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public PriceEntityDataBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public PriceEntityDataBuilder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Price build() {
        return Price.builder()
                .id(id)
                .brandId(brandId)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(priceList)
                .productId(productId)
                .priority(priority)
                .price(price)
                .currency(currency)
                .build();
    }
}
