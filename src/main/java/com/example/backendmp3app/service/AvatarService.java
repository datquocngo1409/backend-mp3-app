package com.example.backendmp3app.service;

import com.example.backendmp3app.model.Avatar;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    List<Avatar> findAll();

    Avatar findById(Long id);

    void remove(Long id);

    Resource findOneAvatar(String name);

    void create(MultipartFile file) throws IOException;

    Avatar findByName(String name);
}
