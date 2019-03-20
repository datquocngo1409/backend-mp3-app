package com.example.backendmp3app.controller;

import com.example.backendmp3app.model.SongCategory;
import com.example.backendmp3app.service.SongCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class SongCategoryController {
    @Autowired
    public SongCategoryService songCategoryService;

    //API sẽ trả về List Category.
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<List<SongCategory>> listAllSongCategories() {
        //Lưu List Category từ Database vào accounts.
        List<SongCategory> accounts = songCategoryService.findAll();
        //Nếu List rỗng thì trả về response No_Content.
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<SongCategory>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        //Trả về Response thành công.
        return new ResponseEntity<List<SongCategory>>(accounts, HttpStatus.OK);
    }

    //API sẽ trả về Category có ID trên url.
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SongCategory> getSongById(@PathVariable("id") long id) {
        System.out.println("Fetching Song Category with id " + id);
        //Tìm Category có ID truyền vào.
        SongCategory songCategory = songCategoryService.findById(id);
        //Nếu không tìm thấy Category.
        if (songCategory == null) {
            System.out.println("Song Category with id " + id + " not found");
            //Trả về Response Not Found.
            return new ResponseEntity<SongCategory>(HttpStatus.NOT_FOUND);
        }
        //Trả về Response thành công.
        return new ResponseEntity<SongCategory>(songCategory, HttpStatus.OK);
    }
}
