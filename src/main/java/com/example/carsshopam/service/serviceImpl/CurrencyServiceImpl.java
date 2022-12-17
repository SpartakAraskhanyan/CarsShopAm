package com.example.carsshopam.service.serviceImpl;


import com.example.carsshopam.model.Currency;
import com.example.carsshopam.repository.CurrencyRepository;
import com.example.carsshopam.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public void save(Currency currency) {
        currencyRepository.save(currency);
    }

    @Override
    public List<Currency> getCurrency() {
        List<Currency> all = currencyRepository.findAll();
        return all;
    }
}
