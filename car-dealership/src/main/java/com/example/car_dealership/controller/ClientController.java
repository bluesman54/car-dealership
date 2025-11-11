package com.example.car_dealership.controller;

import com.example.car_dealership.entity.Client;
import com.example.car_dealership.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    // Страница со списком всех клиентов
    @GetMapping
    public String listClients(Model model) {
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("client", new Client()); // Для формы добавления
        return "clients";
    }

    // Добавление нового клиента
    @PostMapping
    public String addClient(@ModelAttribute Client client) {
        clientService.save(client);
        return "redirect:/clients";
    }

    // Форма редактирования клиента
    @GetMapping("/edit/{id}")
    public String editClientForm(@PathVariable Long id, Model model) {
        Client client = clientService.findById(id);
        model.addAttribute("client", client);
        return "client-edit";
    }

    // Обновление клиента
    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Long id, @ModelAttribute Client client) {
        clientService.update(id, client);
        return "redirect:/clients";
    }

    // Удаление клиента
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return "redirect:/clients";
    }
}