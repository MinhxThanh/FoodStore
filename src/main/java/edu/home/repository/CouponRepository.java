package edu.home.repository;

import edu.home.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query("select cp from Coupon cp left join CustomerCoupon cc on cc.coupon.id = cp.id where cc.customer.email = ?1")
    List<Coupon> findAllByCustomerEmail(String remoteUser);

    Coupon findCouponByCode(String code);
}