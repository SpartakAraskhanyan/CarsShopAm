package com.example.carsshopam.service.serviceImpl;

import com.example.carsshopam.model.AdsImage;
import com.example.carsshopam.repository.AdsRepository;
import com.example.carsshopam.repository.ImagesRepository;
import com.example.carsshopam.service.ImagesService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ImagesServiceImpl implements ImagesService {

    private final ImagesRepository imagesRepository;
    private final AdsRepository adsRepository;

    @Value("${ads.images.path}")
    private String imagePath;

    @SneakyThrows
    @Override
    public byte[] getImage(String imageName) {
        InputStream inputStream = new FileInputStream(imagePath + imageName);
        return IOUtils.toByteArray(inputStream);
    }


    @Override
    public void createImages(int adsId, MultipartFile[] images) {
        adsRepository.findById(adsId).ifPresent(ads -> {
            try {
                for (MultipartFile image : images) {
                    if (!image.isEmpty()) {
                        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                        File newFile = new File(imagePath + fileName);

                        image.transferTo(newFile);

                        AdsImage adsImage = AdsImage.builder()
                                .ads(ads)
                                .imagePath(fileName)
                                .build();
                        imagesRepository.save(adsImage);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
