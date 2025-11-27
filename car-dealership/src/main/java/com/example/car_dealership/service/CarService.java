package com.example.car_dealership.service;

import com.example.car_dealership.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    Car findById(Long id);
    List<Car> findAvailable();
    Car save(Car car);
    Car update(Long id, Car car);
    void markAsSold(Long id);
    void markAsAvailable(Long id);
    void delete(Long id);
}
