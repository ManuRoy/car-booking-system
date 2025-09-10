package car_example.Car_Booking_system.service;


import car_example.Car_Booking_system.dto.CarDTO;
import car_example.Car_Booking_system.exception.ResourceNotFoundException;
import car_example.Car_Booking_system.model.Car;
import car_example.Car_Booking_system.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car createCar(CarDTO carDTO){
        Car car=Car.builder()
                .make(carDTO.getMake())
                .model(carDTO.getModel())
                .year(carDTO.getYear())
                .color(carDTO.getColor())
                .registrationNumber(carDTO.getRegistrationNumber())
                .pricePerDay(carDTO.getPricePerDay())
                .availabilityStatus(carDTO.getAvailabilityStatus())
                .build();
        return carRepository.save(car);
    }

    public List<Car> getAllCars(Boolean available) {
        if (available != null) {
            return carRepository.findByAvailabilityStatus(available);
        }
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
    }



}
