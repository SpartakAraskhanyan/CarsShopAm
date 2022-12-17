package com.example.carsshopam.scheduler;


import com.example.carsshopam.model.Currency;
import com.example.carsshopam.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataOfCurrency {

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;


    @Value("${cb.url}")
    private String cbUrl;

    @Transactional
    @Scheduled(cron = "* * 11 * * ?")
    public void getCurrency() {
        ResponseEntity<Map> currencies = restTemplate.getForEntity(cbUrl, Map.class);
        Map<String, String> hashMap = currencies.getBody();
        Currency currency = currencyRepository.findFirstElement()
                .orElse(new Currency());
        if (hashMap != null) {
            hashMap.entrySet()
                    .stream()
                    .filter(c -> c.getKey().equals("RUB") || c.getKey().equals("USD"))
                    .forEach(stringStringEntry -> {
                        if (stringStringEntry.getKey().equals("RUB")) {
                            currency.setRub(stringStringEntry.getValue());
                        }
                        if (stringStringEntry.getKey().equals("USD")) {
                            currency.setUsd(stringStringEntry.getValue());
                        }
                    });
            currencyRepository.save(currency);
        }

    }


}


