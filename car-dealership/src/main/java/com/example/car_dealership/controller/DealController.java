package com.example.car_dealership.controller;

import com.example.car_dealership.service.CarService;
import com.example.car_dealership.service.ClientService;
import com.example.car_dealership.service.DealService;
import com.example.car_dealership.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;
    private final CarService carService;
    private final ClientService clientService;
    private final UserService userService;

    @GetMapping
    public String listDeals(Model model) {
        model.addAttribute("deals", dealService.findAll());
        model.addAttribute("availableCars", carService.findAvailable());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("managers", userService.findAll());

        return "deals";
    }

    @PostMapping
    public String createDeal(@RequestParam("client") Long clientId,
                             @RequestParam("car") Long carId,
                             @RequestParam("manager") Long managerId) {
        dealService.createDeal(clientId, carId, managerId);
        return "redirect:/deals";
    }

    @GetMapping("/delete/{id}")
    public String deleteDeal(@PathVariable Long id) {
        dealService.delete(id);
        return "redirect:/deals";
    }
}