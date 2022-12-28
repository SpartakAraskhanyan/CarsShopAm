package com.example.carsshopam.service.serviceImpl;

import com.example.carsshopam.dto.AdsRequestDto;
import com.example.carsshopam.dto.AdsResponseDto;
import com.example.carsshopam.dto.AdsUpdateRequestDto;
import com.example.carsshopam.dto.UserDto;
import com.example.carsshopam.exception.AdsNotFoundException;
import com.example.carsshopam.mapper.AdsMapper;
import com.example.carsshopam.model.Ads;
import com.example.carsshopam.model.User;
import com.example.carsshopam.repository.AdsRepository;
import com.example.carsshopam.repository.UserRepository;
import com.example.carsshopam.service.AdsService;
import com.example.carsshopam.util.PriceConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;
    private final UserRepository userRepository;
    private final PriceConverter priceConverter;


    @Override
    public AdsResponseDto create(AdsRequestDto adsRequestDto, User user) {
        Ads ads = adsMapper.map(adsRequestDto);
        ads.setUser(user);
        ads.setDateTime(LocalDateTime.now());
        ads.setActive(true);
        Ads saveAds = adsRepository.save(ads);
        return adsMapper.map(saveAds);


    }

    @Override
    public void save(Ads build) {

    }

    @Override
    public List<AdsResponseDto> findAll() {
        List<Ads> all = adsRepository.findAll();
        var adsResponseDtoList = new ArrayList<AdsResponseDto>();
        if (all.isEmpty()) {
            return new ArrayList<>();
        } else {
            all.forEach(ads -> {
                buildResponseOfAds(adsResponseDtoList, ads);
            });
        }
        return adsResponseDtoList;
    }

    public void buildResponseOfAds(ArrayList<AdsResponseDto> adsResponseDtoList, Ads ads) {
        adsResponseDtoList.add(AdsResponseDto.builder()
                .id(ads.getId())
                .dateTime(ads.getDateTime())
                .carMake(ads.getCarMake())
                .carModel(ads.getCarModel())
                .carYear(ads.getCarYear())
                .carPriceAmd(ads.getCarPriceAmd())
                .carPriceRub(priceConverter.changeToRub(ads.getCarPriceAmd()))
                .carPriceUsd(priceConverter.changeToUsd(ads.getCarPriceAmd()))
                .description(ads.getDescription())
                .active(ads.isActive())
                .userDto(UserDto.builder()
                        .id(ads.getUser().getId())
                        .name(ads.getUser().getName())
                        .surname(ads.getUser().getSurname())
                        .email(ads.getUser().getEmail())
                        .phoneNumber(ads.getUser().getPhoneNumber())
                        .location(ads.getUser().getLocation())
                        .image(ads.getUser().getImage())
                        .build())
                .build());
    }

    @Override
    public Optional<Ads> findById(int id) {
        return adsRepository.findById(id);
    }


    @Override
    public void deleteById(int id) {

    }


    @Override
    public AdsResponseDto updateAds(int id, AdsUpdateRequestDto adsUpdateRequestDto, User user) {
        boolean existsByIdAndUserId = adsRepository.existsByIdAndUserId(id, user.getId());
        if (existsByIdAndUserId) {
            Ads map = adsMapper.map(adsUpdateRequestDto);
            map.setUser(user);
            map.setId(id);
            Ads ads = adsRepository.save(map);
            return adsMapper.map(ads);
        }
        throw new AdsNotFoundException("Ads id '" + id + "' does no exist");
    }

    @Override
    public List<AdsResponseDto> getAllByUserId(int id) {
        var allByUser_id = adsRepository.findAllByUser_Id(id);
        ArrayList<AdsResponseDto> adsResponsesList = new ArrayList<>();
        if (allByUser_id.isEmpty()) {
            return new ArrayList<>();
        } else {
            allByUser_id.forEach(ads -> {
                buildResponseOfAds(adsResponsesList, ads);
            });
            return adsResponsesList;
        }

    }


    @Override
    public List<AdsResponseDto> getFavouritesByUserId(int id) {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            List<AdsResponseDto> adsResponseDtoList = new ArrayList<>();
            for (Ads ads : byId.get().getFavourites()) {
                AdsResponseDto map = adsMapper.map(ads);
                map.setCarPriceRub(priceConverter.changeToRub(ads.getCarPriceAmd()));
                map.setCarPriceUsd(priceConverter.changeToUsd(ads.getCarPriceAmd()));
                adsResponseDtoList.add(map);
            }
            return adsResponseDtoList;
        }
        return new ArrayList<>();
    }


    @Override
    public void deleteFavourites(int adsId, User user) {
        Optional<Ads> byId = adsRepository.findById(adsId);
        byId.ifPresent(ads -> {
            adsRepository.deleteFavouriteByUserIdAndAdsId(user.getId(), adsId);
        });
    }


    @Override
    public void addFavourites(User user, int adsId) {
        Optional<Ads> byId = adsRepository.findById(adsId);

        byId.ifPresent(ads -> {
            if (user.getFavourites() != null) {
                user.getFavourites().add(ads);
            } else {
                Set<Ads> list = new HashSet<>();
                list.add(ads);
                user.setFavourites(list);
            }
            userRepository.save(user);
        });
    }
}
