package com.example.backendmp3app.service;

import com.example.backendmp3app.model.Song;

import java.util.List;

public interface SongService {
    //Hàm trả về List Song.
    List<Song> findAll();

    //Hàm trả về Song có ID tryền vào.
    Song findById(Long id);

    //Hàm lưu một Song truyền vào.
    void save(Song song);

    //Hàm xóa Song với ID truyền vào.
    void remove(Long id);
}
