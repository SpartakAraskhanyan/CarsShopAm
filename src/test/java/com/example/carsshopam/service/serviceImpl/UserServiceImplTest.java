package com.example.carsshopam.service.serviceImpl;

import com.example.carsshopam.model.Role;
import com.example.carsshopam.model.User;
import com.example.carsshopam.repository.UserRepository;
import com.example.carsshopam.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {
    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void register() {
        User user = User.builder()
                .id(1)
                .name("name")
                .role(Role.USER)
                .password("valod")
                .email("valodic@.Email")
                .build();
        when(userRepository.save(any())).thenReturn(user);
        userService.save(User.builder()
                .name("name")
                .role(Role.USER)
                .password("valod")
                .email("valodic@.Email")
                .build());
        verify(userRepository, times(1)).save(any());

    }

    @Test
    void findByEmail() {
        User user = User.builder()
                .id(1)
                .email("777@mail.com")
                .surname("ddddddddddd")
                .build();
        when(userRepository.findByEmail("777@mail.com")).thenReturn(Optional.of(user));
        userService.findByEmail(user.getEmail());
        verify(userRepository, times(1)).findByEmail(any());
    }
}

