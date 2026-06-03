package com.anand.ecommerce_backend.controller;

import com.anand.ecommerce_backend.dto.AuthResponseDTO;
import com.anand.ecommerce_backend.dto.LoginRequestDTO;
import com.anand.ecommerce_backend.dto.RegisterRequestDTO;
import com.anand.ecommerce_backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;

    }
    @PostMapping("/register")
    public AuthResponseDTO register(
            @Valid @RequestBody RegisterRequestDTO dto
            ) {
        return authService.register(dto);
    }
    @PostMapping("/login")
    public AuthResponseDTO login(
            @Valid @RequestBody LoginRequestDTO dto
            ){
        return authService.login(dto);

    }


}
