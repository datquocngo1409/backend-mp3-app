package com.example.backendmp3app.controller;

import com.example.backendmp3app.model.Avatar;
import com.example.backendmp3app.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    //API sẽ trả về List Avatar.
    @RequestMapping(value = "/avatars", method = RequestMethod.GET)
    public ResponseEntity<List<Avatar>> listAllAvatar(Pageable pageable) {
        //Lấy List Avatar từ Database.
        List<Avatar> groups = avatarService.findAll();
        //Nếu List rỗng thì trả về Response No Content.
        if (groups.isEmpty()) {
            return new ResponseEntity<List<Avatar>>(HttpStatus.NO_CONTENT);
        }
        //Trả về Response OK.
        return new ResponseEntity<List<Avatar>>(groups, HttpStatus.OK);
    }

    //API sẽ tạo một Avatar mới.
    @RequestMapping(value = "/avatars", method = RequestMethod.POST)
    public ResponseEntity<?> createAvatar(@RequestParam(name = "file") MultipartFile file, HttpServletRequest servletRequest) throws URISyntaxException {
        try {
            avatarService.create(file);
            System.out.println("Created Avatar!");
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

    @GetMapping("/avatars/{name:.+}/raw")
    public ResponseEntity<?> oneRawAvatar(@PathVariable String name) {
        try {
            Resource file = avatarService.findOneAvatar(name);
            return ResponseEntity.ok().
                    contentLength(file.contentLength()).
                    contentType(MediaType.IMAGE_JPEG).
                    body(new InputStreamResource(file.getInputStream()));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("couldn't find");
        }
    }

    @RequestMapping(value = "/avatars/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Avatar> getAvatarById(@PathVariable("id") long id) {
        System.out.println("Fetching Avatar with id " + id);
        Avatar account = avatarService.findById(id);
        if (account == null) {
            System.out.println("Image with id " + id + " not found");
            return new ResponseEntity<Avatar>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Avatar>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/avatarname/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Avatar> getAvatarByName(@PathVariable("id") String id) {
        System.out.println("Fetching Avatar with name " + id);
        Avatar account = avatarService.findByName(id);
        if (account == null) {
            System.out.println("Avatar with name " + id + " not found");
            return new ResponseEntity<Avatar>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Avatar>(account, HttpStatus.OK);
    }
}
