package com.example.carsshopam.service;

import com.example.carsshopam.model.Currency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyService {

    void save(Currency currency);

    List<Currency> getCurrency();

}
