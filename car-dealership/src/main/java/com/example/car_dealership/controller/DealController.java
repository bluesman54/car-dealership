package com.example.car_dealership.controller;

import com.example.car_dealership.entity.Car;
import com.example.car_dealership.entity.Client;
import com.example.car_dealership.entity.Deal;
import com.example.car_dealership.entity.User;
import com.example.car_dealership.service.CarService;
import com.example.car_dealership.service.ClientService;
import com.example.car_dealership.service.DealService;
import com.example.car_dealership.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;
    private final CarService carService;
    private final ClientService clientService;
    private final UserService userService;

    // Страница со списком всех сделок
    @GetMapping
    public String listDeals(Model model) {
        List<Deal> deals = dealService.findAll();
        model.addAttribute("deals", deals);

        // Данные для формы добавления
        model.addAttribute("deal", new Deal());
        model.addAttribute("availableCars", carService.findAvailable());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("managers", userService.findAll());

        return "deals";
    }

    // Создание новой сделки
    @PostMapping
    public String createDeal(@ModelAttribute Deal deal) {
        dealService.createDeal(deal);
        return "redirect:/deals";
    }

    // Удаление сделки
    @GetMapping("/delete/{id}")
    public String deleteDeal(@PathVariable Long id) {
        dealService.delete(id);
        return "redirect:/deals";
    }
}