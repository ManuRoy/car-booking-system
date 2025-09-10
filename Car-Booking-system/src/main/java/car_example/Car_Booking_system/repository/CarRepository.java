package car_example.Car_Booking_system.repository;

import car_example.Car_Booking_system.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByAvailabilityStatus(Boolean status);
}