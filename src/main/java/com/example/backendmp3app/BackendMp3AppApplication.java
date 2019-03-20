package com.example.backendmp3app;

import com.example.backendmp3app.service.*;
import com.example.backendmp3app.service.impl.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendMp3AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendMp3AppApplication.class, args);
    }

    @Bean
    public AdminService adminService() {
        return new AdminServiceImpl();
    }

    @Bean
    public SongCategoryService songCategoryService() {
        return new SongCategoryServiceImpl();
    }

    @Bean
    public SongService songService() {
        return new SongServiceImpl();
    }

    @Bean
    public ImageService imageService() {
        return new ImageServiceImpl();
    }

    @Bean
    public Mp3FileService mp3FileService() {
        return new Mp3FileServiceImpl();
    }

}
