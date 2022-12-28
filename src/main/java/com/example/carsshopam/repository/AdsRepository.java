package com.example.carsshopam.repository;

import com.example.carsshopam.model.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface AdsRepository extends JpaRepository<Ads,Integer>, QuerydslPredicateExecutor<Ads> {

    boolean existsByIdAndUserId(int id, int userId);

    List<Ads> findAllByUser_Id(int id);

    List<Ads> getFavouritesByUserId(int id);

    @Query(value = "delete from favourites where ads_id=:adsId and user_id=:userId", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteFavouriteByUserIdAndAdsId(@Param("userId") int userId, @Param("adsId") int adsId);
}

