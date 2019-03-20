package com.example.backendmp3app.service;
import com.example.backendmp3app.model.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    //Hàm trả về List Ảnh.
    List<Image> findAll();

    //Hàm trả về Ảnh với tên truyền vào.
    Resource findOneImage(String name);

    //Hàm thêm Ảnh.
    void create(MultipartFile file) throws IOException;

    //Hàm xóa Ảnh.
    void delete(String name) throws IOException;

    //Hàm trả về Ảnh có ID truyền vào.
    Image findById(Long id);
}
