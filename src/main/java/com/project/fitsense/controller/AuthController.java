package com.project.fitsense.controller;

import com.project.fitsense.dto.request.RegisterRequest;
import com.project.fitsense.dto.response.RegisterResponse;
import com.project.fitsense.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<RegisterResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
