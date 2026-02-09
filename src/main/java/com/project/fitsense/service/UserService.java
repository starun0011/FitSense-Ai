package com.project.fitsense.service;

import com.project.fitsense.dto.request.RegisterRequest;
import com.project.fitsense.dto.response.RegisterResponse;
import com.project.fitsense.entity.User;
import com.project.fitsense.exception.NotFoundException;
import com.project.fitsense.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public RegisterResponse registerUser(RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .weight(request.getWeight())
                .build();
        User savedUser = userRepository.save(user);
        return userDtoMapper(savedUser);
    }

    private RegisterResponse userDtoMapper(User savedUser) {
        return RegisterResponse.builder()
                .userID(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .weight(savedUser.getWeight())
                .createdAt(String.valueOf(savedUser.getCreatedAt()))
                .updatedAt(String.valueOf(savedUser.getUpdatedAt()))
                .build();
    }

    public List<RegisterResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::userDtoMapper).toList();
    }
}
