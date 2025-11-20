package com.example.car_dealership.service;

import com.example.car_dealership.entity.Car;
import com.example.car_dealership.enums.CarStatus;
import com.example.car_dealership.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarService {
    private final CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findAvailable() {
        return carRepository.findByStatus(CarStatus.AVAILABLE);
    }

    public Car findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }

    @Transactional
    public Car save(Car car) {
        if (carRepository.existsByVin(car.getVin())) {
            throw new RuntimeException("Car with VIN " + car.getVin() + " already exists");
        }
        return carRepository.save(car);
    }

    @Transactional
    public Car update(Long id, Car car) {
        Car existingCar = findById(id);
        existingCar.setBrand(car.getBrand());
        existingCar.setModel(car.getModel());
        existingCar.setYear(car.getYear());
        existingCar.setColor(car.getColor());
        existingCar.setPrice(car.getPrice());

        return carRepository.save(existingCar);
    }

    @Transactional
    public void markAsSold(Long id) {
        carRepository.updateStatus(id, CarStatus.SOLD);
        log.info("Car {} marked as SOLD", id);
    }

    @Transactional
    public void markAsAvailable(Long id) {
        carRepository.updateStatus(id, CarStatus.AVAILABLE);
        log.info("Car {} marked as AVAILABLE", id);
    }

    @Transactional
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}