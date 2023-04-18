package edu.home.controller.rest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.common.entity.AvgReview;
import edu.home.entity.Report;
import edu.home.entity.Review;
import edu.home.service.ReviewService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/review")
public class ReviewRestController {
	@Autowired
	ReviewService reviewService;
	
	@GetMapping(value = "getAll")
	public List<AvgReview> getAvgReview() {
		return reviewService.getAvgReview();
	}
	
	@GetMapping(value = "findById/{id}")
	public List<Review> findByFoodID(@PathVariable("id") Long id) {
		return reviewService.findAllByFoodId(id);
	}
	
	@DeleteMapping(value = "delete/{id}")
	public void deleteReview(@PathVariable("id") Long id) {
		try {
			reviewService.delete(id);
			System.out.println(id);
			System.out.println("dang day delete review");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}