package com.example.carsshopam.util;

import com.example.carsshopam.model.Currency;
import com.example.carsshopam.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class PriceConverter {


    private final CurrencyService currencyService;


    public Double changeToRub(Double amd) {
        List<Currency> currencies = currencyService.getCurrency();

        if (currencies.stream().findFirst().isPresent()) {
            Double rub = Double.parseDouble(currencies.get(0).getRub());
            return amd/ rub;
        }
        return 0.0;
    }

    public Double changeToUsd(Double amd) {
        List<Currency> currencies = currencyService.getCurrency();

        if (currencies.stream().findFirst().isPresent()) {
            Double usd = Double.parseDouble(currencies.get(0).getUsd());
            return amd/usd;
        }
        return 0.0;
    }

}
