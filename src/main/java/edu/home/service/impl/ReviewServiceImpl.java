package edu.home.service.impl;

import edu.home.entity.Review;
import edu.home.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements edu.home.service.ReviewService {
    @Autowired
    private ReviewRepository dao;
    @Override
    public Review findByCustomerEmailAndFoodId(String email, Long foodId) {
        return dao.findByCustomerEmailAndFoodId(email, foodId);
    }

    @Override
    public void updateRatingByCustomerEmailAndFoodId(Long rateValue, Date date, String email, Long foodId) {
        dao.updateRatingByCustomerEmailAndFoodId(rateValue, date, email, foodId);
    }

    @Override
    public Review create(Review review1) {
        return dao.save(review1);
    }

    @Override
    public List<Review> findAllByFoodId(Long foodId) {
        return dao.findAllByFoodId(foodId);
    }
}
