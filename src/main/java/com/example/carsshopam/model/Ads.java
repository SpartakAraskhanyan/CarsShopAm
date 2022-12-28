package com.example.carsshopam.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "ads")
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    private LocalDateTime dateTime;
    private String carMake;
    private String carModel;
    private String carYear;
    private double carPriceAmd;
    private String description;
    private boolean active;



}
