package com.example.carsshopam.scheduler;

import com.example.carsshopam.repository.AdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class CheckExpiredAds {


    private final AdsRepository adsRepository;


    @Scheduled(cron = "* * 12 * * ?")
    public void activationAds() {
        adsRepository.findAll().forEach(ads -> {
            LocalDateTime monthAgo = LocalDateTime.now().minusDays(30);
            if (ads.getDateTime().isBefore(monthAgo)) {
                ads.setActive(false);
                adsRepository.save(ads);
            }
        });
    }
}
