package com.example.car_dealership.controller;

import com.example.car_dealership.entity.Car;
import com.example.car_dealership.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    // Страница со списком всех автомобилей
    @GetMapping
    public String listCars(Model model) {
        List<Car> cars = carService.findAll();
        model.addAttribute("cars", cars);
        model.addAttribute("car", new Car()); // Для формы добавления
        return "cars";
    }

    // Страница только с доступными автомобилями
    @GetMapping("/available")
    public String availableCars(Model model) {
        List<Car> availableCars = carService.findAvailable();
        model.addAttribute("cars", availableCars);
        model.addAttribute("car", new Car());
        return "cars";
    }

    // Добавление нового автомобиля (обработка формы)
    @PostMapping
    public String addCar(@ModelAttribute Car car) {
        // Устанавливаем статус "Доступен" по умолчанию
        car.setStatus(Car.CarStatus.AVAILABLE);
        carService.save(car);
        return "redirect:/cars";
    }

    // Форма редактирования автомобиля
    @GetMapping("/edit/{id}")
    public String editCarForm(@PathVariable Long id, Model model) {
        Car car = carService.findById(id);
        model.addAttribute("car", car);
        return "car-edit";
    }

    // Обновление автомобиля
    @PostMapping("/update/{id}")
    public String updateCar(@PathVariable Long id, @ModelAttribute Car car) {
        // Сохраняем текущий статус при обновлении
        Car existingCar = carService.findById(id);
        car.setStatus(existingCar.getStatus());
        carService.update(id, car);
        return "redirect:/cars";
    }

    // Удаление автомобиля
    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.delete(id);
        return "redirect:/cars";
    }
}