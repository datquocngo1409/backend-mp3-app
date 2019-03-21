package com.example.backendmp3app.service;
import com.example.backendmp3app.model.Mp3File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface Mp3FileService {
    //Hàm trả về List Mp3File.
    List<Mp3File> findAll();

    //Hàm trả về Mp3File với tên truyền vào.
    Resource findOneMp3File(String name);

    //Hàm thêm một Mp3File.
    void create(MultipartFile file) throws IOException;

    //Hàm xóa một Mp3File.
    void delete(String name) throws IOException;

    //Hàm trả về Mp3File có ID truyền vào.
    Mp3File findById(Long id);

    Mp3File findByName(String name);
}
