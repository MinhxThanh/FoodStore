package edu.home.service;

import edu.home.entity.Review;

import java.util.Date;
import java.util.List;

public interface ReviewService {
    Review findByCustomerEmailAndFoodId(String email, Long foodId);

    void updateRatingByCustomerEmailAndFoodId(Long rateValue, Date date, String email, Long foodId);

    Review create(Review review1);

    List<Review> findAllByFoodId(Long foodId);
}
