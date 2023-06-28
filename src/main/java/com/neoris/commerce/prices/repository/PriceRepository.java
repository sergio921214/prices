package com.neoris.commerce.prices.repository;

import com.neoris.commerce.prices.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = "SELECT * FROM prices " +
            "WHERE brand_id = ?1 AND product_id = ?2 AND start_date <= ?3 AND end_date >= ?3 " +
            "ORDER BY priority DESC " +
            "LIMIT 1", nativeQuery = true)
    Optional<Price> findFirstByBrandIdAndProductIdAndDate(Long brandId, Long productId, LocalDateTime date);

}







