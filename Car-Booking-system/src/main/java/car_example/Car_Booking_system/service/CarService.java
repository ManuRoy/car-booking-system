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

    public Car updateCar(Long id, CarDTO carDTO) {
        Car car = getCarById(id);

        car.setMake(carDTO.getMake() != null ? carDTO.getMake() : car.getMake());
        car.setModel(carDTO.getModel() != null ? carDTO.getModel() : car.getModel());
        car.setYear(carDTO.getYear() != null ? carDTO.getYear() : car.getYear());
        car.setColor(carDTO.getColor() != null ? carDTO.getColor() : car.getColor());
        car.setRegistrationNumber(carDTO.getRegistrationNumber() != null ? carDTO.getRegistrationNumber() : car.getRegistrationNumber());
        car.setPricePerDay(carDTO.getPricePerDay() != null ? carDTO.getPricePerDay() : car.getPricePerDay());
        car.setAvailabilityStatus(carDTO.getAvailabilityStatus() != null ? carDTO.getAvailabilityStatus() : car.getAvailabilityStatus());

        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            throw new RuntimeException("Car not found to  " + id);
        }
        carRepository.deleteById(id);
    }

}
