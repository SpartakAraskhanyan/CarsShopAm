package com.example.carsshopam.dto;


import lombok.Data;

@Data

public class AdsFilterDto {

    private String carMake;
    private String carModel;
    private String minYear;
    private String maxYear;
    private double minPrice;
    private double maxPrice;


}
