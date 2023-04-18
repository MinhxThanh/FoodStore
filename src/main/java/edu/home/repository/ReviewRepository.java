package edu.home.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import edu.home.common.entity.AvgReview;
import edu.home.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByCustomerEmailAndFoodId(String email, Long foodId);

    @Transactional
    @Modifying
    @Query("update Review r set r.rating =?1, r.updateDate = ?2 where r.customer.email =?3 and r.food.id =?4")
    void updateRatingByCustomerEmailAndFoodId(Long rateValue, Date date, String email, Long foodId);

    List<Review> findAllByFoodId(Long foodId);
    
    
    @Query("SELECT new AvgReview (avg(r.rating),r.food)"
    		+ " FROM Review r"
    		+ "  GROUP BY r.food"
    		+ "  ORDER BY avg(r.rating) desc")
    List<AvgReview>getAvgReview();
}
