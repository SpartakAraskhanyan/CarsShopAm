package com.example.carsshopam.dto;

import com.example.carsshopam.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdsResponseDto {

    private int id;
    private String carMake;
    private String carModel;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;
    private String carYear;
    private double carPriceUsd;
    private double carPriceAmd;
    private double carPriceRub;
    private String description;
    private boolean active;
    private UserDto userDto;
}
