package edu.home.controller.rest.controller;

import edu.home.entity.ImageBlog;
import edu.home.service.ImageBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/imageBlog")
public class ImageBlogRestController {
    @Autowired
    private ImageBlogService imageBlogService;

    @GetMapping(value = "findAllByBlogId/{blogId}")
    public ResponseEntity<?> findAllByBlogId(@PathVariable("blogId") Long blogId){
        try {
            return ResponseEntity.ok(imageBlogService.findAllByBlogId(blogId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "create")
    public ResponseEntity<?> createImageBlog(@RequestBody ImageBlog imageBlog) {
        try {
            return ResponseEntity.ok(imageBlogService.create(imageBlog));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id){
        try {
            imageBlogService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
