package car_example.Car_Booking_system.service;

import car_example.Car_Booking_system.config.JwtUtils;
import car_example.Car_Booking_system.dto.RegisterRequest;
import car_example.Car_Booking_system.model.Role;
import car_example.Car_Booking_system.model.User;
import car_example.Car_Booking_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already taken!");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();

        return userRepository.save(user);
    }

    }