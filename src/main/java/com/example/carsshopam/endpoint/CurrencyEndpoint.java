package com.example.carsshopam.endpoint;


import com.example.carsshopam.model.Currency;
import com.example.carsshopam.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyEndpoint {

    private final CurrencyService currencyService;


    @PostMapping
    public ResponseEntity<Currency> saveCurrency(@RequestBody Currency currency) {
        currencyService.save(currency);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Currency> getCurrency() {
        List<Currency> all = currencyService.getCurrency();
        return all;
    }



}