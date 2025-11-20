package com.example.car_dealership.service;

import com.example.car_dealership.entity.*;
import com.example.car_dealership.enums.CarStatus;
import com.example.car_dealership.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DealService {
    private final DealRepository dealRepository;
    private final CarService carService;
    private final ClientService clientService;
    private final UserService userService;

    public List<Deal> findAll() {
        return dealRepository.findAll();
    }

    private Deal buildDeal(Client client, Car car, User manager) {
        return Deal.builder()
                .client(client)
                .car(car)
                .manager(manager)
                .salePrice(car.getPrice())
                .build();
    }

    @Transactional
    public Deal createDeal(Long clientId, Long carId, Long managerId) {
        Client client = clientService.findById(clientId);
        Car car = carService.findById(carId);
        User manager = userService.findById(managerId);

        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new RuntimeException("Car is not available for sale");
        }

        Deal deal = buildDeal(client, car,manager);
        carService.markAsSold(car.getId());

        Deal savedDeal = dealRepository.save(deal);
        log.info("Created deal {} for client {} and car {}", savedDeal.getId(), client.getId(), car.getId());

        return savedDeal;
    }

    @Transactional
    public void delete(Long id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found with id: " + id));
        Car car = deal.getCar();

        carService.markAsAvailable(car.getId());
        dealRepository.deleteById(id);
    }
}