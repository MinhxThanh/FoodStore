package edu.home.service;

import java.util.Date;
import java.util.List;

import edu.home.entity.CustomerCoupon;

public interface CustomerCouponService {
	List<CustomerCoupon> findByEmail(String email);
	List<CustomerCoupon> findByCouponId(Long code);
	void deleteById(Long id);
	CustomerCoupon save(CustomerCoupon customerCoupon);
	List<CustomerCoupon> findAll();
	void customerCouponService(String emailCustomer, Long idCoupon);
	
	void deleteByNameAndFoodId(String name, Long foodId);
	
//	Giau
	CustomerCoupon findByCustomerEmailAndCouponId(String email, Long id);
	CustomerCoupon create(CustomerCoupon cusCp);

	void updateCustomerCouponStatusByCustomerEmailAndCouponId(long l, String email, long id);

	CustomerCoupon getByCustomerEmailAndCouponId(String email, long couponId);
}
