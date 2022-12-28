package com.example.carsshopam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdsDto {

    private int id;
    private UserDto userDto;
    private Date dateTime;
    private String carMake;
    private String carModel;
    private String carYear;
    private double carPriceUsd;
    private double carPriceAmd;
    private double carPriceRub;
    private String description;

}
