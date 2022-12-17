package com.example.carsshopam.repository;

import com.example.carsshopam.model.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;


public interface AdsRepository extends JpaRepository<Ads,Integer>, QuerydslPredicateExecutor<Ads> {

    boolean existsByIdAndUserId(int id, int userId);

    List<Ads> findAllByUser_Id(int id);

    List<Ads> getFavouritesByUserId(int id);
}

