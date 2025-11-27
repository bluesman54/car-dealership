package com.example.car_dealership.service;

import com.example.car_dealership.entity.Deal;

import java.util.List;

public interface DealService {
    List<Deal> findAll();
    Deal createDeal(Long clientId, Long carId, Long managerId);
    void delete(Long id);
}
