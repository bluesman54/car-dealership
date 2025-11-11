package com.example.car_dealership.controller;

import com.example.car_dealership.service.CarService;
import com.example.car_dealership.service.ClientService;
import com.example.car_dealership.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final CarService carService;
    private final ClientService clientService;
    private final DealService dealService;

    @GetMapping("/")
    public String dashboard(Model model) {
        // Статистика для дашборда
        model.addAttribute("totalCars", carService.findAll().size());
        model.addAttribute("availableCars", carService.findAvailable().size());
        model.addAttribute("totalClients", clientService.findAll().size());
        model.addAttribute("totalDeals", dealService.findAll().size());

        // Последние 5 автомобилей
        model.addAttribute("recentCars", carService.findAll().stream()
                .limit(5)
                .toList());

        // Последние 5 клиентов
        model.addAttribute("recentClients", clientService.findAll().stream()
                .limit(5)
                .toList());

        // Последние 5 сделок
        model.addAttribute("recentDeals", dealService.findAll().stream()
                .limit(5)
                .toList());

        return "dashboard";
    }
}
