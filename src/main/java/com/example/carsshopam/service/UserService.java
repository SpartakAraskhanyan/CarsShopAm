package com.example.carsshopam.service;

import com.example.carsshopam.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> findAll();

    Optional<User> findById(int id);

    User save(User user);

    void deleteById(int id);


    Optional<User> findByEmail(String email);

}
