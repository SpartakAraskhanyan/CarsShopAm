package com.example.carsshopam.endpoint;

import com.example.carsshopam.dto.AdsFilterDto;
import com.example.carsshopam.dto.AdsRequestDto;
import com.example.carsshopam.dto.AdsResponseDto;
import com.example.carsshopam.dto.AdsUpdateRequestDto;
import com.example.carsshopam.exception.AdsNotFoundException;
import com.example.carsshopam.model.Ads;
import com.example.carsshopam.model.User;
import com.example.carsshopam.repository.CustomAdsRepository;
import com.example.carsshopam.security.CurrentUser;
import com.example.carsshopam.service.AdsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsEndpoint {

    private final AdsService adsService;
    private final CustomAdsRepository customAdsRepository;

    Logger log = LoggerFactory.getLogger(AdsEndpoint.class.getName());


    @Operation(
            operationId = "getAllAds",
            summary = "Get all Ads",
            description = "Get aal Ads description"
    )


    @GetMapping("/filter")
    public List<AdsResponseDto> getAllFilteredAds(@AuthenticationPrincipal CurrentUser currentUser, @RequestBody AdsFilterDto adsFilterDto) {
        log.info("endpoint/ads called by " + currentUser.getUser().getEmail());

        ArrayList<AdsResponseDto> result = new ArrayList<>();
        for (Ads ads : customAdsRepository.ads(adsFilterDto)) {
            adsService.buildResponseOfAds(result, ads);
        }
        return result;
    }


    @GetMapping
    public ResponseEntity<List<AdsResponseDto>> getAllAds() {
        return new ResponseEntity<>(adsService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdsResponseDto> addNewAds(@RequestBody AdsRequestDto adsRequestDto,
                                                    @AuthenticationPrincipal CurrentUser currentUser) {

        return ResponseEntity.ok(adsService.create(adsRequestDto, currentUser.getUser()));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateAds(@RequestBody AdsUpdateRequestDto updateRequestDto,
                                       @PathVariable("id") int id,
                                       @AuthenticationPrincipal CurrentUser currentUser) {


        return ResponseEntity.ok(adsService.updateAds(id, updateRequestDto, currentUser.getUser()));

    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getAdsById(@PathVariable("id") int id) throws AdsNotFoundException {
        return ResponseEntity.ok(adsService.findById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {

        User user = currentUser.getUser();
        Optional<Ads> byId = adsService.findById(id);
        if (byId.isPresent()) {
            Ads ads = byId.get();
            if (ads.getUser().getId() == user.getId()) {
                adsService.deleteById(id);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my")
    public ResponseEntity<List<AdsResponseDto>> getAllByUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return new ResponseEntity<>(adsService.getAllByUserId(currentUser.getUser().getId()), HttpStatus.OK);
    }

    @PostMapping("/favourites/{id}")
    public ResponseEntity<?> addFavourites(@PathVariable("id") int adsId, @AuthenticationPrincipal CurrentUser currentUser) {
        adsService.addFavourites(currentUser.getUser(), adsId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my/favourites")
    public ResponseEntity<List<AdsResponseDto>> getFavouritesByUser(@AuthenticationPrincipal CurrentUser currentUser) {
//        currentUser.getUser()
        return new ResponseEntity<>(adsService.getFavouritesByUserId(currentUser.getUser().getId()), HttpStatus.OK);
    }

    @DeleteMapping("/my/favourites/{id}")
    public ResponseEntity<?> deleteFavourites(@PathVariable("id") int adsId, @AuthenticationPrincipal CurrentUser currentUser){
       adsService.deleteFavourites(adsId, currentUser.getUser());
        return ResponseEntity.ok().build();

    }

}
