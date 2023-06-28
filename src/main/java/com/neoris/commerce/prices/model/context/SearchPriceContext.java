package com.neoris.commerce.prices.model.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchPriceContext {
    private LocalDateTime applicationDate;
    private Long productId;
    private Long brandId;
}
