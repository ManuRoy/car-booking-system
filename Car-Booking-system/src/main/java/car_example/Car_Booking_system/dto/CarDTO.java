package car_example.Car_Booking_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private String make;
    private String model;
    private Integer year;
    private String color;
    private String registrationNumber;
    private Double pricePerDay;
    private Boolean availabilityStatus;
}