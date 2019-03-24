package com.example.backendmp3app.repository;

import com.example.backendmp3app.model.Avatar;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {
    Avatar findByName(String name);
}
