package com.example.carsshopam.repository;

import com.example.carsshopam.dto.AdsFilterDto;
import com.example.carsshopam.model.Ads;
import com.example.carsshopam.model.QAds;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CustomAdsRepository {


    @PersistenceContext
    private EntityManager entityManager;

    public CustomAdsRepository() {
    }

    public List<Ads> ads(AdsFilterDto adsFilterDto) {
        QAds qAds = QAds.ads;
        var jpaQuery = new JPAQuery(entityManager);
        var from = jpaQuery.from(qAds);

        if (adsFilterDto.getCarMake() != null && !adsFilterDto.getCarMake().equals("")) {
            from.where(qAds.carMake.contains(adsFilterDto.getCarMake()));
        }
        if (adsFilterDto.getCarModel() != null && !adsFilterDto.getCarModel().equals("")) {
            from.where(qAds.carModel.contains(adsFilterDto.getCarModel()));
        }
        if (adsFilterDto.getMinYear() != null && !adsFilterDto.getMinYear().equals("") && adsFilterDto.getMaxYear() != null && !adsFilterDto.getMaxYear().equals("")) {
            from.where(qAds.carYear.goe(adsFilterDto.getMinYear()).and(qAds.carYear.loe(adsFilterDto.getMaxYear())));
        }
        if (adsFilterDto.getMinPrice() != 0 && adsFilterDto.getMaxPrice() > adsFilterDto.getMinPrice()) {
            from.where(qAds.carPriceAmd.goe(adsFilterDto.getMinPrice()).and(qAds.carPriceAmd.loe(adsFilterDto.getMaxPrice())));
        }

        return from.fetch();


    }

}
