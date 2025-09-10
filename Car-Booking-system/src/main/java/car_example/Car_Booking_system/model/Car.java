package car_example.Car_Booking_system.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;

    private String model;

    private Integer year;

    private String color;

    private String registrationNumber;

    private Double pricePerDay;

    private Boolean availabilityStatus;


}
