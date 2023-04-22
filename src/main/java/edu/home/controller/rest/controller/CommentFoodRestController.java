package edu.home.controller.rest.controller;

import edu.home.entity.Comment;
import edu.home.entity.Review;
import edu.home.service.CommentService;
import edu.home.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/commentFood")
public class CommentFoodRestController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(commentService.getAll());
        } catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "getAllByFoodId/{foodId}")
    public ResponseEntity<?> getAllByFoodId(@PathVariable("foodId") Long foodId){
        try {
            return ResponseEntity.ok(commentService.getAllByFoodId(foodId));
        } catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "getInfoReviewOfCustomer/{foodId}/{email}")
    public ResponseEntity<?> getInfoReviewOfCustomer(@PathVariable("foodId") Long foodId, @PathVariable("email") String email){
        try {
            Review review = reviewService.findByCustomerEmailAndFoodId(email, foodId);
            System.out.println("review: " + review.getCustomer().getEmail());
            return ResponseEntity.ok(review);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "create")
    public ResponseEntity<?> create(@RequestBody Comment comment) {
        try {
            return ResponseEntity.ok(commentService.create(comment));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "delete/{id}")
    public void delete(@PathVariable("id") Long id){
        commentService.deleteById(id);
    }
}
