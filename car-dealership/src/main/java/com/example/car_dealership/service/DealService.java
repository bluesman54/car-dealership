package com.example.car_dealership.service;

import com.example.car_dealership.entity.*;
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

    public Deal findById(Long id) {
        return dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found with id: " + id));
    }

    @Transactional
    public Deal createDeal(Deal deal) {
        Client client = clientService.findById(deal.getClient().getId());
        Car car = carService.findById(deal.getCar().getId());
        User manager = userService.findById(deal.getManager().getId());

        if (car.getStatus() != Car.CarStatus.AVAILABLE) {
            throw new RuntimeException("Car is not available for sale");
        }

        deal.setClient(client);
        deal.setCar(car);
        deal.setManager(manager);

        // Помечаем автомобиль как проданный
        carService.markAsSold(car.getId());

        Deal savedDeal = dealRepository.save(deal);
        log.info("Created deal {} for client {} and car {}", savedDeal.getId(), client.getId(), car.getId());

        return savedDeal;
    }

    @Transactional
    public void delete(Long id) {
        dealRepository.deleteById(id);
    }
}