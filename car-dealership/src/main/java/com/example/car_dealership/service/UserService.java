package com.example.car_dealership.service;

import com.example.car_dealership.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User findByLogin(String login);
    User save(User user);
    User update(Long id, User user);
    void delete(Long id);
}
