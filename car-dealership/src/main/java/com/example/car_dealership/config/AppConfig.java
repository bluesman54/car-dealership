package com.example.car_dealership.config;

import com.example.car_dealership.entity.*;
import com.example.car_dealership.repository.CarRepository;
import com.example.car_dealership.repository.ClientRepository;
import com.example.car_dealership.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository,
                                      ClientRepository clientRepository,
                                      CarRepository carRepository) {
        return args -> {
            // Создание тестовых пользователей
            if (userRepository.count() == 0) {
                userRepository.save(User.builder()
                        .login("admin")
                        .password("admin123")
                        .role("ADMIN")
                        .build());
            }

            // Создание тестовых клиентов
            if (clientRepository.count() == 0) {
                clientRepository.save(Client.builder()
                        .firstName("Иван")
                        .lastName("Иванов")
                        .phone("+7-999-123-45-67")
                        .email("ivan@mail.ru")
                        .build());

                clientRepository.save(Client.builder()
                        .firstName("Петр")
                        .lastName("Петров")
                        .phone("+7-888-765-43-21")
                        .email("petr@mail.ru")
                        .build());

                clientRepository.save(Client.builder()
                        .firstName("Мария")
                        .lastName("Сидорова")
                        .phone("+7-777-111-22-33")
                        .email("maria@mail.ru")
                        .build());
            }

            // Создание тестовых автомобилей
            if (carRepository.count() == 0) {
                carRepository.save(Car.builder()
                        .brand("Toyota")
                        .model("Camry")
                        .year(2023)
                        .vin("JTNB11GX0M3012345")
                        .color("Black")
                        .price(new BigDecimal("2500000"))
                        .build());

                carRepository.save(Car.builder()
                        .brand("Honda")
                        .model("Accord")
                        .year(2022)
                        .vin("1HGCV1F30MA012345")
                        .color("White")
                        .price(new BigDecimal("2200000"))
                        .build());

                carRepository.save(Car.builder()
                        .brand("BMW")
                        .model("X5")
                        .year(2023)
                        .vin("5UXCR6C05P9A12345")
                        .color("Blue")
                        .price(new BigDecimal("5500000"))
                        .build());

                carRepository.save(Car.builder()
                        .brand("Mercedes")
                        .model("E-Class")
                        .year(2024)
                        .vin("W1K1770840V123456")
                        .color("Silver")
                        .price(new BigDecimal("4800000"))
                        .build());
            }
        };
    }
}
