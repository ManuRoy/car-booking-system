package car_example.Car_Booking_system.Controller;


import car_example.Car_Booking_system.dto.LoginRequest;
import car_example.Car_Booking_system.dto.LoginResponse;
import car_example.Car_Booking_system.dto.RegisterRequest;
import car_example.Car_Booking_system.model.User;
import car_example.Car_Booking_system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}