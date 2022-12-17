package com.example.carsshopam.repository;

import com.example.carsshopam.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

    @Query(value = "select * from currency limit 1;", nativeQuery = true)
    Optional<Currency> findFirstElement();
}
