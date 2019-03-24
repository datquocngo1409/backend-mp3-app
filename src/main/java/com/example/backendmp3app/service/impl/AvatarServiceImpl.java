package com.example.backendmp3app.service.impl;

import com.example.backendmp3app.model.Avatar;
import com.example.backendmp3app.model.Image;
import com.example.backendmp3app.repository.AvatarRepository;
import com.example.backendmp3app.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AvatarServiceImpl implements AvatarService {

    private String UPLOAD = "upload-avatar";

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public List<Avatar> findAll() {
        return (List<Avatar>) avatarRepository.findAll();
    }

    @Override
    public Avatar findById(Long id) {
        return avatarRepository.findById(id).get();
    }

    @Override
    public void create(MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            Files.copy(file.getInputStream(), Paths.get(UPLOAD, file.getOriginalFilename()));
            avatarRepository.save(new Avatar(file.getOriginalFilename()));
        }
    }

    @Override
    public Avatar findByName(String name) {
        return avatarRepository.findByName(name);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Resource findOneAvatar(String name) {
        return resourceLoader.getResource("file:"+UPLOAD+"/"+name);
    }
}
