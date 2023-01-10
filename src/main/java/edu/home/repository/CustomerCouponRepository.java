package edu.home.repository;

import edu.home.entity.CustomerCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCouponRepository extends JpaRepository<CustomerCoupon, Long> {
}