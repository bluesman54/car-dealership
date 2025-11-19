package com.example.car_dealership.entity;

import com.example.car_dealership.enums.CarStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    private Integer year;

    @Column(unique = true)
    private String vin;

    private String color;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    @CreationTimestamp
    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

}