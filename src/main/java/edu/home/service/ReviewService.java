package edu.home.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.home.common.entity.AvgReview;
import edu.home.entity.Review;
@Service
public interface ReviewService {
	
	Review findByCustomerEmailAndFoodId(String email, Long foodId);

	void updateRatingByCustomerEmailAndFoodId(Long rateValue, Date date, String email, Long foodId);

	Review create(Review review1);

	List<Review> findAllByFoodId(Long foodId);

	List<AvgReview> getAvgReview();

	void delete(Long id);

}
