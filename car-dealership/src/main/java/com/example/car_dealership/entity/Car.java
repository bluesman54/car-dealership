package com.example.car_dealership.entity;

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
    @Builder.Default
    private CarStatus status = CarStatus.AVAILABLE;

    @CreationTimestamp
    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    public enum CarStatus {
        AVAILABLE, // не продано
        SOLD       // продано
    }
}
