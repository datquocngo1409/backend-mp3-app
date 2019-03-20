package com.example.backendmp3app.repository;

import com.example.backendmp3app.model.Mp3File;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface Mp3FileRepository extends PagingAndSortingRepository<Mp3File, Long> {
}
