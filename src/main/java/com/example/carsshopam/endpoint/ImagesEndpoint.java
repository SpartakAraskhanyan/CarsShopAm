package com.example.carsshopam.endpoint;

import com.example.carsshopam.service.ImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ImagesEndpoint {

    private final ImagesService imagesService;

    @PostMapping("/images/{id}")
    public ResponseEntity<?> addImageToAds(@PathVariable("id") int id, @RequestParam MultipartFile[] images) {
        imagesService.createImages(id, images);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/images", produces = "image/jpeg")
    public @ResponseBody
    byte[] getImage(@RequestParam("images") String imageName) {
        return imagesService.getImage(imageName);
    }

}
