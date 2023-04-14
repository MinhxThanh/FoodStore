package edu.home.controller.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.entity.Review;
import edu.home.service.ReviewService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/review")
public class ReviewRestController {
	@Autowired
	ReviewService reviewService;

	@GetMapping(value = "getAll")
	public List<Review> getAllblog() {
		return reviewService.findAll();
	}
	@DeleteMapping(value = "delete/{id}")
	public void deleteReview(@PathVariable("id") Long id) {
		reviewService.delete(id);
		System.out.println("dang day delete review");
	}


}