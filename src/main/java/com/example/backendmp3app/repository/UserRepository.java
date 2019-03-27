package com.example.backendmp3app.repository;

import com.example.backendmp3app.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String name);
}
