package com.example.car_dealership.repository;

import com.example.car_dealership.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByStatus(Car.CarStatus status);
    Optional<Car> findByVin(String vin);

    @Modifying
    @Query("UPDATE Car c SET c.status = :status WHERE c.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") Car.CarStatus status);

    boolean existsByVin(String vin);
}
