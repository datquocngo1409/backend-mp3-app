package com.example.backendmp3app.controller;

import com.example.backendmp3app.model.Image;
import com.example.backendmp3app.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
//Cho phép Angular có thể truy cập vào Backend.
@CrossOrigin(value = "*", maxAge = 3600)
public class ImageController {

    @Autowired
    private ImageService imageService;

    //API sẽ trả về List Image.
    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public ResponseEntity<List<Image>> listAllGroup(Pageable pageable) {
        //Lấy List Image từ Database.
        List<Image> groups = imageService.findAll();
        //Nếu List rỗng thì trả về Response No Content.
        if (groups.isEmpty()) {
            return new ResponseEntity<List<Image>>(HttpStatus.NO_CONTENT);
        }
        //Trả về Response OK.
        return new ResponseEntity<List<Image>>(groups, HttpStatus.OK);
    }

    //API sẽ tạo một Image mới.
    @RequestMapping(value = "/images", method = RequestMethod.POST)
    public ResponseEntity<?> createStaff(@RequestParam(name = "file") MultipartFile file, HttpServletRequest servletRequest) throws URISyntaxException {
        try {
            imageService.create(file);
            System.out.println("Created Image!");
            HttpHeaders headers = new HttpHeaders();
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            headers.setAccessControlAllowOrigin("*");
            headers.setAccessControlAllowCredentials(true);
            URI locationUri = new URI(servletRequest.getRequestURI().toString() + "/")
                    .resolve(file.getOriginalFilename() + "/raw");
            return ResponseEntity.created(locationUri)
                    .body("success upload");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("fail" + "=>" + e.getMessage());
        }
    }
//
//    @RequestMapping(value = "/images", method = RequestMethod.POST)
//    public ResponseEntity<?> createStaff(@RequestParam(name = "file") MultipartFile file, HttpServletRequest servletRequest, UriComponentsBuilder ucBuilder) throws URISyntaxException {
//        try {
//            imageService.create(file);
//            List<Image> imageList = imageService.findAll();
//            Image  image = null;
//            for (Image img : imageList) {
//                if (img.getName().equals(file.getName())) {
//                    image = img;
//                }
//            }
//            HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(ucBuilder.path("/images/{id}").buildAndExpand(image.getId()).toUri());
//            URI locationUri = new URI(servletRequest.getRequestURI().toString() + "/")
//                    .resolve(file.getOriginalFilename() + "/raw");
//            return ResponseEntity.created(locationUri)
//                    .body("success upload");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("fail" + "=>" + e.getMessage());
//        }
//    }

    //API sẽ trả về Ảnh trong url localhost/ + tên ảnh +/raw.
    @GetMapping("/images/{name:.+}/raw")
    public ResponseEntity<?> oneRawImage(@PathVariable String name) {
        try {
            Resource file = imageService.findOneImage(name);
            return ResponseEntity.ok().
                    contentLength(file.contentLength()).
                    contentType(MediaType.IMAGE_JPEG).
                    body(new InputStreamResource(file.getInputStream()));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("couldn't find");
        }
    }

    @RequestMapping(value = "/images/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Image> getImageById(@PathVariable("id") long id) {
        System.out.println("Fetching Image with id " + id);
        Image account = imageService.findById(id);
        if (account == null) {
            System.out.println("Image with id " + id + " not found");
            return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Image>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/imagesname/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Image> getImageByName(@PathVariable("id") String id) {
        System.out.println("Fetching Image with name " + id);
        Image account = imageService.findByName(id);
        if (account == null) {
            System.out.println("Image with name " + id + " not found");
            return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Image>(account, HttpStatus.OK);
    }
}
