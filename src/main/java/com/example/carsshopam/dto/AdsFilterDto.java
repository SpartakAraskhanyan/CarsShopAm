package com.example.carsshopam.dto;


import lombok.Data;

import java.time.LocalDate;

@Data

public class AdsFilterDto {

    private String carMake;
    private String carModel;
    private LocalDate carYear;
    private double carPrice;


}
