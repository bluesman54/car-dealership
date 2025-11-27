package com.example.car_dealership.service.impl;

import com.example.car_dealership.entity.*;
import com.example.car_dealership.enums.CarStatus;
import com.example.car_dealership.repository.DealRepository;
import com.example.car_dealership.service.CarService;
import com.example.car_dealership.service.ClientService;
import com.example.car_dealership.service.DealService;
import com.example.car_dealership.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DealServiceImpl implements DealService {
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
            throw new RuntimeException("Недоступно для продажи");
        }

        Deal deal = buildDeal(client, car,manager);
        carService.markAsSold(car.getId());

        Deal savedDeal = dealRepository.save(deal);
        log.info("Создана сделка {} клиентом {} на машину {}", savedDeal.getId(), client.getId(), car.getId());

        return savedDeal;
    }

    @Transactional
    public void delete(Long id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Сделка не найдена: " + id));
        Car car = deal.getCar();

        carService.markAsAvailable(car.getId());
        dealRepository.deleteById(id);
    }
}