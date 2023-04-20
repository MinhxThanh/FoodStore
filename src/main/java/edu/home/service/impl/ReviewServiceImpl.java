package edu.home.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.home.common.entity.AvgReview;
import edu.home.entity.Review;
import edu.home.repository.ReviewRepository;

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
    @Override
    public List<AvgReview> getAvgReview() {
        return dao.getAvgReview();
    }

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
		
	}

    @Override
    public List<Review> getRatingBetweenByFoodId(Long foodId, double v, double i) {
        return dao.getRatingBetweenByFoodId(foodId, v, i);
    }

    @Override
    public AvgReview findByFoodId(Long foodId) {
        return dao.findByFoodId(foodId);
    }

}
