package edu.home.repository;

import java.util.Date;
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

//	Giau
	@Query("select c from CustomerCoupon c where c.customer.email=?1 and c.coupon.id=?2")
	CustomerCoupon findByCustomerEmailAndCouponId(String email, Long id);

	@Transactional
	@Modifying
	@Query("update CustomerCoupon c set c.status = :status where c.customer.email = :email and c.coupon.id = :couponId")
	void updateCustomerCouponStatusByCustomerEmailAndCouponId(long status, String email, long couponId);

	@Query("select c from CustomerCoupon c where c.customer.email = :email and c.coupon.id = :couponId")
	CustomerCoupon getByCustomerEmailAndCouponId(String email, long couponId);
}
