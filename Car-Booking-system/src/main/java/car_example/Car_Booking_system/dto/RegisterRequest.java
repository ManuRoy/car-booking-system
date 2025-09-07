package car_example.Car_Booking_system.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role; // "USER" or "ADMIN"
}
