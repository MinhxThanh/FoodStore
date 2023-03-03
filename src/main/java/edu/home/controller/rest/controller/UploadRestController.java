package edu.home.controller.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.home.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "rest/upload")
public class UploadRestController {
    @Autowired
    private UploadService uploadService;

    @PostMapping(value = "images/{folder}")
    public JsonNode upload(@PathVariable("folder") String folder, @PathParam("file") MultipartFile file) {
        File save = uploadService.save(file, folder);
        ObjectMapper mapper =new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("name", save.getName());
        node.put("size", save.length());
        return node;
    }
}
