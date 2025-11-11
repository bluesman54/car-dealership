package com.example.car_dealership.service;

import com.example.car_dealership.entity.User;
import com.example.car_dealership.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("User not found with login: " + login));
    }

    @Transactional
    public User save(User user) {
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new RuntimeException("User with login " + user.getLogin() + " already exists");
        }
        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User user) {
        User existingUser = findById(id);
        existingUser.setLogin(user.getLogin());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
