package com.example.carsshopam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdsRequestDto {

    private String carMake;
    private String carModel;
    private String carYear;
    private double carPriceAmd;
    private String description;

}
