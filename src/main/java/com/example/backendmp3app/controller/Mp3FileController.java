package com.example.backendmp3app.controller;

import com.example.backendmp3app.model.Mp3File;
import com.example.backendmp3app.service.Mp3FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
//Cho phép Angular có thể truy cập vào Backend.
@CrossOrigin("*")
public class Mp3FileController {
    private static final String BASE_PATH = "/mp3files";
    private static final String FILENAME = "{filename:.+}";

    @Autowired
    private Mp3FileService mp3FileService;

    //API sẽ trả về List Mp3File.
    @RequestMapping(value = "/mp3files",method = RequestMethod.GET)
    public ResponseEntity<List<Mp3File>> listAllGroup(Pageable pageable){
        //Lưu List từ Database vào groups.
        List<Mp3File> groups = mp3FileService.findAll();
        //Nếu List rỗng trả về response No Content.
        if (groups.isEmpty()){
            return new ResponseEntity<List<Mp3File>>(HttpStatus.NO_CONTENT);
        }
        //Trả về Response thành công.
        return new ResponseEntity<List<Mp3File>>(groups, HttpStatus.OK);
    }

    //API sẽ tạo một Mp3File.
    @RequestMapping(value = "/mp3files",method = RequestMethod.POST)
    public ResponseEntity<?> createMp3File(@RequestParam(name = "fileMp3") MultipartFile file, HttpServletRequest servletRequest) throws URISyntaxException {
        try{
            //Tạo file.
            mp3FileService.create(file);
            System.out.println("Created!");
            HttpHeaders headers = new HttpHeaders();
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            //Set url để truy cập vào file đó, ví dụ http://localhost:8080/mp3files/Cang-Niu-Giu-Cang-De-Mat-Mr-Siro.mp3/raw.
            URI locationUri = new URI(servletRequest.getRequestURI().toString() + "/")
                    .resolve(file.getOriginalFilename() + "/raw");
            //Trả về Response Created.
            return ResponseEntity.created(locationUri)
                    .body("success upload");
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("fail" + "=>" + e.getMessage());
        }
    }

    //Hàm set link truy cập file mp3 (localhost + filename + /raw) ví dụ http://localhost:8080/mp3files/Cang-Niu-Giu-Cang-De-Mat-Mr-Siro.mp3/raw.
    @GetMapping("/mp3files/{name:.+}/raw")
    public ResponseEntity<?> oneRawMp3File (@PathVariable String name){
        try {
            //Tìm file với tên trên url.
            Resource file = mp3FileService.findOneMp3File(name);
            //2 dòng này để tìm type của file, cũng không hiểu lăm.
            Path path = Paths.get("/media/psmaster/HDD/TUC-IPS/" + name);
            String contentType = Files.probeContentType(path);
            //Hàm này trả về Response file với type trên.
            return ResponseEntity.ok().
                    contentLength(file.contentLength()).
                    contentType(MediaType.parseMediaType(contentType)).
                    body(new InputStreamResource(file.getInputStream()));
        } catch (IOException e){
            return ResponseEntity.badRequest().body("couldn't find");
        }
    }
}
