package com.example.backendmp3app.repository;

import com.example.backendmp3app.model.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminRepository extends PagingAndSortingRepository<Admin, Long> {
}
