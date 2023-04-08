package edu.home.controller.rest.controller;

import edu.home.common.entity.CommentBlogRequest;
import edu.home.entity.CommentsBlog;
import edu.home.entity.Customer;
import edu.home.service.BlogService;
import edu.home.service.CommentBlogService;
import edu.home.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "rest/commentBlog")
public class CommentBlogRestController {
    @Autowired
    private CommentBlogService commentBlogService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CustomerService customerService;


    @GetMapping(value = "findByBlogId/{blogId}")
    public ResponseEntity<?> findByBlogId(@PathVariable("blogId") Long blogId){
        try {
            return ResponseEntity.ok(commentBlogService.findAllByBlogId(blogId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "create")
    public ResponseEntity<?> create(@RequestBody CommentBlogRequest commentBlogRequest, HttpServletRequest request){
        try {
            Customer customer = customerService.findByEmailKey(request.getRemoteUser());
            CommentsBlog item = new CommentsBlog();
            item.setBlog(blogService.findBlogsById(commentBlogRequest.getBlogId()));
            item.setCustomer(customer);
            item.setTitle(customer.getLastName() + " " + customer.getFirstName());
            item.setContent(commentBlogRequest.getContent());
            item.setCreateDate(new Date());
            item.setUpdateDate(new Date());
            item.setStatus(1);
            item.setDisplay(true);
            return ResponseEntity.ok(commentBlogService.create(item));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        commentBlogService.deleteById(id);
    }
}
