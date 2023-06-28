package com.neoris.commerce.prices.controller;

import com.neoris.commerce.prices.model.PriceResponse;
import com.neoris.commerce.prices.repository.PriceRepository;
import com.neoris.commerce.prices.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/prices")
@AllArgsConstructor
public class PriceController {

    @Autowired
    private PriceService service;

    @GetMapping
    public ResponseEntity<PriceResponse> getPrices(
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("applicationDate", applicationDate);
        arguments.put("productId", productId);
        arguments.put("brandId", brandId);

        return ResponseEntity.ok(service.handle(arguments));
    }

}
