package com.anand.ecommerce_backend.service;
import com.anand.ecommerce_backend.dto.AuthResponseDTO;
import com.anand.ecommerce_backend.dto.LoginRequestDTO;
import com.anand.ecommerce_backend.dto.RegisterRequestDTO;
import com.anand.ecommerce_backend.entity.Role;
import com.anand.ecommerce_backend.entity.User;
import com.anand.ecommerce_backend.exception.EmailALreadyExistsException;
import com.anand.ecommerce_backend.exception.InvalidCredentialsException;
import com.anand.ecommerce_backend.repository.UserRepository;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public AuthResponseDTO register(RegisterRequestDTO dto){
        if (userRepository.existsByEmail(dto.getEmail())){
            throw new EmailALreadyExistsException(
                    "Email already registered"
            );
        }
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(
                        passwordEncoder.encode(dto.getPassword())
                )
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
        return new AuthResponseDTO(
                "User registered successfully"
        );
    }

    public AuthResponseDTO login(LoginRequestDTO dto){
          User user = userRepository.findByEmail(dto.getEmail())
                  .orElseThrow(() ->
                          new InvalidCredentialsException(
                                  "Invalid email or password"
                          )
                  );
          boolean matches = passwordEncoder.matches(
                  dto.getPassword(),
                  user.getPassword()
          );
          if (!matches){
              throw new InvalidCredentialsException(
                      "Invalid email or password"
              );
          }
          return new AuthResponseDTO(
                  "Login successful"
          );
    }

}
