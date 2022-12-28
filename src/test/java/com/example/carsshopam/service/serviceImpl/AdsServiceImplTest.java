package com.example.carsshopam.service.serviceImpl;

import com.example.carsshopam.model.Ads;
import com.example.carsshopam.model.User;
import com.example.carsshopam.repository.AdsRepository;
import com.example.carsshopam.service.AdsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdsServiceImplTest {

    @MockBean
    AdsRepository adsRepository;

    @Autowired
    AdsService adsService;

    @Test
    void findAll() {
        adsService.findAll();
        verify(adsRepository).findAll();
    }

    @Test
    void save() {
        Ads ads = Ads.builder()
                .id(1)
                .dateTime(LocalDateTime.now())
                .carMake("mers")
                .carModel("e500")
                .carPriceAmd(100000.500)
                .carYear(String.valueOf(LocalDate.of(2010, 12, 12)))
                .description("poioijhb")
                .user(User.builder().build())
                .build();
        when(adsRepository.save(any())).thenReturn(ads);
        adsService.save(Ads.builder()
                .dateTime(LocalDateTime.now())
                .carMake("mers")
                .carModel("e500")
                .carPriceAmd(100000.500)
                .carYear(String.valueOf(LocalDate.of(2010, 12, 12)))
                .description("poioijhb")
                .user(User.builder().build())
                .build());
        verify(adsRepository).save(any());

    }

    @Test
    void update() {
        Ads ads = Ads.builder()
                .id(1)
                .dateTime(LocalDateTime.now())
                .carMake("mers")
                .carModel("e500")
                .carPriceAmd(100000.500)
                .carYear(String.valueOf(LocalDate.of(2010, 12, 12)))
                .description("poioijhb")
                .user(User.builder().build())
                .build();
        when(adsRepository.save(any())).thenReturn(ads);
        Ads adsForUpdate = Ads.builder()
                .id(1)
                .dateTime(LocalDateTime.now())
                .carMake("mers")
                .carModel("e500")
                .carPriceAmd(100000.500)
                .carYear(String.valueOf(LocalDate.of(2010, 12, 12)))
                .description("poioijhb")
                .user(User.builder().build())
                .build();
//        assertSame(ads, adsService.updateAds(adsForUpdate));
//        verify(adsRepository).save(any());

    }

    @Test
    void deleteById() {
        Ads ads = Ads.builder()
                .id(1)
                .dateTime(LocalDateTime.now())
                .carMake("mers")
                .carModel("e500")
                .carPriceAmd(100000.500)
                .carYear(String.valueOf(LocalDate.of(2010, 12, 12)))
                .description("poioijhb")
                .user(User.builder().build())
                .build();
        adsService.deleteById(ads.getId());
        verify(adsRepository).deleteById(any());
    }

    @Test
    void findAdsById() {
        Ads ads = Ads.builder()
                .id(1)
                .dateTime(LocalDateTime.now())
                .carMake("mers")
                .carModel("e500")
                .carPriceAmd(100000.500)
                .carYear(String.valueOf(LocalDate.of(2010, 12, 12)))
                .description("poioijhb")
                .user(User.builder().build())
                .build();
        when(adsRepository.findById(any())).thenReturn(Optional.of(ads));
        adsService.findById(ads.getId());
        verify(adsRepository).findById(any());
    }
}
