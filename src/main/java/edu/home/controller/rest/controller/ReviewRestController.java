package edu.home.controller.rest.controller;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import edu.home.common.entity.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.common.entity.AvgReview;

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

	@GetMapping(value = "getRatingByFoodId/{foodId}")
	public ResponseEntity<?> getRatingByFoodId(@PathVariable("foodId") Long foodId) {
		try {
			Integer excellent = reviewService.getRatingBetweenByFoodId(foodId, 4.5, 5).size();
			Integer good = reviewService.getRatingBetweenByFoodId(foodId, 4, 4.5).size();
			Integer average = reviewService.getRatingBetweenByFoodId(foodId, 3, 4).size();
			Integer poor = reviewService.getRatingBetweenByFoodId(foodId, 1, 2).size();
			Integer terrible = reviewService.getRatingBetweenByFoodId(foodId, 0, 1).size();

			BigDecimal total = BigDecimal.valueOf(reviewService.findAllByFoodId(foodId).size());
			BigDecimal excellentPer = getCalculatePercentage(BigDecimal.valueOf(excellent), total);
			BigDecimal goodPer = getCalculatePercentage(BigDecimal.valueOf(good), total);
			BigDecimal averagePer = getCalculatePercentage(BigDecimal.valueOf(average), total);
			BigDecimal poorPer = getCalculatePercentage(BigDecimal.valueOf(poor), total);
			BigDecimal terriblePer = getCalculatePercentage(BigDecimal.valueOf(terrible), total);
			return ResponseEntity.ok(new RatingResponse(excellentPer, goodPer, averagePer, poorPer, terriblePer, excellent, good, average, poor, terrible, total));
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(value = "findOneByFoodId/{id}")
	public ResponseEntity<?> findOneByFoodId(@PathVariable("id") Long foodId){
		try {
			return ResponseEntity.ok(reviewService.findByFoodId(foodId));
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
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

	private BigDecimal getCalculatePercentage(BigDecimal a, BigDecimal b) {
		return a.divide(b, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
	}
}