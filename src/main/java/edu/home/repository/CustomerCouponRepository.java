package edu.home.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.home.entity.CustomerCoupon;

public interface CustomerCouponRepository extends JpaRepository<CustomerCoupon, Long> {
	@Query("select c from CustomerCoupon c where c.customer.email like %:email%")
	List<CustomerCoupon> findByEmail(@Param("email") String email);
	@Query("select c from CustomerCoupon c where c.coupon.id=?1")
	List<CustomerCoupon> findByCouponId(Long couponId);
	
	@Transactional
	@Modifying
	@Query("delete from CustomerCoupon c where c.customer.email like %:email% and c.coupon.id= :idCoupon")
	void deleteByEmailCustomerAndIdCoupon(@Param("email") String emailCustomer, @Param("idCoupon")Long idCoupon);
}
