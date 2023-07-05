package com.neoris.commerce.prices.model.context;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SearchPriceContext {
    private LocalDateTime applicationDate;
    private Long productId;
    private Long brandId;
}
