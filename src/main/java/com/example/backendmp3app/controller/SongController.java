package com.example.backendmp3app.controller;

import com.example.backendmp3app.model.Song;
import com.example.backendmp3app.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
//Cho phép localhost:4200 có thể truy cập vào Backend.
@CrossOrigin("*")
public class SongController {
    @Autowired
    public SongService songService;

    //API sẽ trả về List Song.
    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public ResponseEntity<List<Song>> listAllSongs() {
        //Lưu list từ Database vào accounts.
        List<Song> accounts = songService.findAll();
        //Nếu list rỗng thì trả về Respone NO_CONTENT.
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<Song>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        //Trả về Respone có List.
        return new ResponseEntity<List<Song>>(accounts, HttpStatus.OK);
    }

    //API sẽ trả về Song có ID trên url.
    //Chi tiết như hàm List.
    @RequestMapping(value = "/songs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Song> getSongById(@PathVariable("id") long id) {
        System.out.println("Fetching Song with id " + id);
        Song song = songService.findById(id);
        if (song == null) {
            System.out.println("Song with id " + id + " not found");
            return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Song>(song, HttpStatus.OK);
    }

    //API sẽ thêm một Song mới.
    @RequestMapping(value = "/songs", method = RequestMethod.POST)
    public ResponseEntity<Void> createSong(@RequestBody Song song, UriComponentsBuilder ucBuilder) {
        songService.save(song);
        System.out.println("Created Song!");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/songs/{id}").buildAndExpand(song.getId()).toUri());
        //Trả về Response đã tạo mới.
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API sẽ cập nhật Song với id trên url.
    @RequestMapping(value = "/songs/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Song> updateSong(@PathVariable("id") long id, @RequestBody Song song, UriComponentsBuilder ucBuilder) {
        System.out.println("Updating Song " + id);

        //Lấy về Song có ID trên url.
        Song currentSong = songService.findById(id);

        //Nếu không tìm được Song có ID như thế.
        if (currentSong == null) {
            System.out.println("Song with id " + id + " not found");
            //Trả về Respone Not found.
            return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
        }

        //Nếu tìm được Song có ID trên url, cập nhật lại Song đó.
        currentSong.setName(song.getName());
        currentSong.setDescription(song.getDescription());
        currentSong.setSinger_name(song.getSinger_name());
        currentSong.setImage(song.getImage());
        currentSong.setMp3File(song.getMp3File());
        currentSong.setId(song.getId());
        currentSong.setListenCount(song.getListenCount());

        songService.save(currentSong);
        //Trả về Response thành công.
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/songs/{id}").buildAndExpand(currentSong.getId()).toUri());
        return new ResponseEntity<Song>(currentSong, HttpStatus.OK);
    }

    //API sẽ xóa Song với ID trên url.
    @RequestMapping(value = "/songs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Song> deleteAdmin(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Song with id " + id);

        //Tìm Song có ID trên url.
        Song song = songService.findById(id);
        //Nếu không timf được.
        if (song == null) {
            System.out.println("Unable to delete. Song with id " + id + " not found");
            return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
        }

        //Xóa Song đó.
        songService.remove(id);
        //Trả về Response No_Content.
        return new ResponseEntity<Song>(HttpStatus.NO_CONTENT);
    }
}
