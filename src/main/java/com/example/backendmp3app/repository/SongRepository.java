package com.example.backendmp3app.repository;

import com.example.backendmp3app.model.Song;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SongRepository extends PagingAndSortingRepository<Song, Long> {
}
