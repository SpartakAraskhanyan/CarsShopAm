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

    @Mapping(target = "userDto", source = "user")
    @Mapping(target = "carPriceAmd",source = "carPriceAmd")
    @Mapping(target = "carPriceRub" ,ignore = true)
    @Mapping(target = "carPriceUsd",ignore = true)
    AdsResponseDto map(Ads ads);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Ads map(AdsUpdateRequestDto updateRequestDto);


}
