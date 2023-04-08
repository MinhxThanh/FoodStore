package edu.home.controller.rest.controller;

import edu.home.entity.CategoryBlog;
import edu.home.service.CategoryBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/categoryBlog")
public class CategoryBlogRestController {
    @Autowired
    private CategoryBlogService categoryBlogService;

    @GetMapping(value = "findAll")
    public ResponseEntity<?> findAll(){
        try {
            return ResponseEntity.ok(categoryBlogService.findAll());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "create")
    public ResponseEntity<?> createCategoryFood(@RequestBody CategoryBlog categoryBlog){
        try {
            return ResponseEntity.ok(categoryBlogService.create(categoryBlog));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "deleteByBlogIdAndCategoryId/{blogId}/{categoryId}")
    public void deleteByBlogIdAndCategoryId(@PathVariable("blogId") Long blogId, @PathVariable("categoryId") Long categoryId){
        categoryBlogService.deleteByBlogIdAndCategoryId(blogId, categoryId);
    }
}
