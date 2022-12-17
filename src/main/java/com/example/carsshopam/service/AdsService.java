package com.example.carsshopam.service;

import com.example.carsshopam.dto.AdsRequestDto;
import com.example.carsshopam.dto.AdsResponseDto;
import com.example.carsshopam.dto.AdsUpdateRequestDto;
import com.example.carsshopam.model.Ads;
import com.example.carsshopam.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public interface AdsService {

    List<AdsResponseDto> findAll();

    Optional<Ads> findById(int id);


    void deleteById(int id);

    AdsResponseDto updateAds(int id, AdsUpdateRequestDto updateRequestDto, User user);


    AdsResponseDto create(AdsRequestDto adsRequestDto, User user);


    void save(Ads build);

    List<AdsResponseDto> getAllByUserId(int id);

    void addFavourites(User user, int id);

    List<AdsResponseDto> getFavouritesByUserId(int id);
}
