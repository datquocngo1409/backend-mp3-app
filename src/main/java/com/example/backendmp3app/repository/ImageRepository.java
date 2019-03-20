package com.example.backendmp3app.repository;

import com.example.backendmp3app.model.Image;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {
    Image findByName(String filename);
}
