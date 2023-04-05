package edu.home.controller.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.home.service.FirebaseStorageService;
import edu.home.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "rest/upload")
public class UploadRestController {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private FirebaseStorageService storageService;

    @PostMapping(value = "images/{folder}")
    public JsonNode upload(@PathVariable("folder") String folder, @PathParam("file") MultipartFile file) {
        File save = uploadService.save(file, folder);
        storageService.upload(file);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("name", save.getName());
        node.put("size", save.length());
        return node;
    }

    @PostMapping(value = "firebase")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("name", storageService.upload(multipartFile));
        return node;
    }
}
