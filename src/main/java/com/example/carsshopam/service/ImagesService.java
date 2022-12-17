package com.example.carsshopam.service;

import com.example.carsshopam.model.AdsImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ImagesService {


    default List<AdsImage> findAllImages() {
        return null;
    }

    default AdsImage findById(int id) {
        return null;
    }

    default AdsImage deleteById(int id) {
        return null;
    }

    byte[] getImage(String id);

    void createImages(int id, MultipartFile[] images);
}
