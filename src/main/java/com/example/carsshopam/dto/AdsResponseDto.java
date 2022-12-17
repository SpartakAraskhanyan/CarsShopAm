package com.example.carsshopam.dto;

import com.example.carsshopam.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate carYear;
    private double carPrice;
    private String description;
    private boolean isActive;
    private User user;
}
