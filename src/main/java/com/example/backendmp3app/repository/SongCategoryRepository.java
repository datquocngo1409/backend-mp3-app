package com.example.backendmp3app.repository;

import com.example.backendmp3app.model.SongCategory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SongCategoryRepository extends PagingAndSortingRepository<SongCategory, Long> {
}
