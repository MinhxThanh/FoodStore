package edu.home.repository;

import edu.home.entity.FoodCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCouponRepository extends JpaRepository<FoodCoupon, Long> {
}