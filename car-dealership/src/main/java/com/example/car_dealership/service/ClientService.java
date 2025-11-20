package com.example.car_dealership.service;

import com.example.car_dealership.entity.Client;
import com.example.car_dealership.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Клиент не найден с id: " + id));
    }

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Client update(Long id, Client client) {
        Client existingClient = findById(id);
        existingClient.setFirstName(client.getFirstName());
        existingClient.setLastName(client.getLastName());
        existingClient.setPhone(client.getPhone());
        existingClient.setEmail(client.getEmail());
        return clientRepository.save(existingClient);
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
