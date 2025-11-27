package com.example.car_dealership.service;

import com.example.car_dealership.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();
    Client findById(Long id);
    Client save(Client client);
    void delete(Long id);
}
