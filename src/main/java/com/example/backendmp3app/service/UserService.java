package com.example.backendmp3app.service;

import com.example.backendmp3app.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void remove(Long id);

    User findByName(String name);
}
