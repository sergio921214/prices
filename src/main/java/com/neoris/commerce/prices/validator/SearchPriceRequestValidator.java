package com.neoris.commerce.prices.validator;

import com.neoris.commerce.prices.model.context.SearchPriceContext;
import com.neoris.commerce.prices.model.exception.PriceNotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SearchPriceRequestValidator {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public void validate(SearchPriceContext context) {
        validateNotNull(context.getApplicationDate(), "applicationDate");
        validateNotNull(context.getProductId(), "productId");
        validateNotNull(context.getBrandId(), "brandId");
        validateDateFormat(context.getApplicationDate(), "applicationDate");
    }

    private void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new PriceNotFoundException(fieldName + " cannot be null");
        }
    }

    private void validateDateFormat(LocalDateTime date, String fieldName) {
        String formattedDate = DATE_FORMATTER.format(date);
        try {
            LocalDateTime.parse(formattedDate, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new PriceNotFoundException(fieldName + " has an invalid date format");
        }
    }
}

