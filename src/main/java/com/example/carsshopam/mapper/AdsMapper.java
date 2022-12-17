package com.example.carsshopam.mapper;

import com.example.carsshopam.dto.AdsRequestDto;
import com.example.carsshopam.dto.AdsResponseDto;
import com.example.carsshopam.dto.AdsUpdateRequestDto;
import com.example.carsshopam.model.Ads;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdsMapper {
   @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ads map(AdsRequestDto adsRequestDto);

    AdsResponseDto map(Ads ads);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Ads map(AdsUpdateRequestDto updateRequestDto);




}
