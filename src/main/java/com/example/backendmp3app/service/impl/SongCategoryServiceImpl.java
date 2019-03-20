package com.example.backendmp3app.service.impl;

import com.example.backendmp3app.model.SongCategory;
import com.example.backendmp3app.repository.SongCategoryRepository;
import com.example.backendmp3app.service.SongCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//Đọc comment ở File Service.
public class SongCategoryServiceImpl implements SongCategoryService {

    @Autowired
    private SongCategoryRepository songCategoryRepository;

    @Override
    public List<SongCategory> findAll() {
        return (List<SongCategory>) songCategoryRepository.findAll();
    }

    @Override
    public SongCategory findById(Long id) {
        return songCategoryRepository.findById(id).get();
    }

    @Override
    public void save(SongCategory songCategory) {
        songCategoryRepository.save(songCategory);
    }

    @Override
    public void remove(Long id) {
        songCategoryRepository.deleteById(id);
    }
}
