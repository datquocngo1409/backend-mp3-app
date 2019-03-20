package com.example.backendmp3app.service.impl;


import com.example.backendmp3app.model.Mp3File;
import com.example.backendmp3app.repository.Mp3FileRepository;
import com.example.backendmp3app.service.Mp3FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

//Đọc comment ở File Service.
public class Mp3FileServiceImpl implements Mp3FileService {
    //Thư lục lưu file mp3.
    private String UPLOAD = "upload-mp3";

    @Autowired
    private Mp3FileRepository mp3FileRepository;

    @Autowired
    private ResourceLoader resourceLoader;


    @Override
    public List<Mp3File> findAll() {
        return (List<Mp3File>) mp3FileRepository.findAll();
    }

    @Override
    public Resource findOneMp3File(String name) {
        return resourceLoader.getResource("file:"+UPLOAD+"/"+name);
    }

    @Override
    public void create(MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            Files.copy(file.getInputStream(), Paths.get(UPLOAD, file.getOriginalFilename()));
            mp3FileRepository.save(new Mp3File(file.getOriginalFilename()));
        }
    }

    @Override
    public void delete(String name) throws IOException {

    }

    @Override
    public Mp3File findById(Long id) {
        return mp3FileRepository.findById(id).get();
    }
}
