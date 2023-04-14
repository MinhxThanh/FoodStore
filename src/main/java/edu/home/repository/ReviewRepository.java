package edu.home.repository;

import edu.home.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByCustomerEmailAndFoodId(String email, Long foodId);

    @Transactional
    @Modifying
    @Query("update Review r set r.rating =?1, r.updateDate = ?2 where r.customer.email =?3 and r.food.id =?4")
    void updateRatingByCustomerEmailAndFoodId(Long rateValue, Date date, String email, Long foodId);

    List<Review> findAllByFoodId(Long foodId);
}
