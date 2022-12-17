package com.example.carsshopam.service.serviceImpl;

import com.example.carsshopam.dto.AdsRequestDto;
import com.example.carsshopam.dto.AdsResponseDto;
import com.example.carsshopam.dto.AdsUpdateRequestDto;
import com.example.carsshopam.exception.AdsNotFoundException;
import com.example.carsshopam.mapper.AdsMapper;
import com.example.carsshopam.model.Ads;
import com.example.carsshopam.model.User;
import com.example.carsshopam.repository.AdsRepository;
import com.example.carsshopam.repository.UserRepository;
import com.example.carsshopam.service.AdsService;
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
                adsResponseDtoList.add(AdsResponseDto.builder()
                        .id(ads.getId())
                        .carMake(ads.getCarMake())
                        .carModel(ads.getCarModel())
                        .carPrice(ads.getCarPrice())
                        .carYear(ads.getCarYear())
                        .dateTime(ads.getDateTime())
                        .description(ads.getDescription())
                        .isActive(ads.isActive())
                        .user(ads.getUser()).build());
            });
        }
        return adsResponseDtoList;
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
        List<AdsResponseDto> adsResponsesList = new ArrayList<>();
        if (allByUser_id.isEmpty()) {
            return new ArrayList<>();
        } else {
            allByUser_id.forEach(ads -> {
                adsResponsesList.add(adsMapper.map(ads));
            });
            return adsResponsesList;
        }

    }


    @Override
    public List<AdsResponseDto> getFavouritesByUserId(int id) {
        Optional<User> likerOptional = userRepository.findById(id);
        List<Ads> adsList = new ArrayList<>();
        User user = null;
        if (likerOptional.isPresent()) {
            user = likerOptional.get();
        }
        List<Ads> all = adsRepository.findAll();
        Set<User> userSet = new HashSet<>();
        userSet.add(user);

        for (Ads ads : all) {
            if (ads.getUserSet().containsAll(userSet)) {
                adsList.add(ads);
            }
        }
        List<AdsResponseDto> adsResponseDtoList = new ArrayList<>();
        for (Ads ads : adsList) {
            adsResponseDtoList.add(adsMapper.map(ads));
        }
        return adsResponseDtoList;
    }


    @Override
    public void addFavourites(User user, int id) {
        Optional<Ads> byId = adsRepository.findById(id);

        byId.ifPresent(ads -> {
            ads.getUserSet().add(user);
            adsRepository.save(ads);
        });


    }
}
