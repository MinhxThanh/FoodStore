package edu.home.service;

import java.util.List;

import edu.home.entity.Coupon;

public interface CouponService {
	List<Coupon> findAllCouponByCustomerByEmail(String remoteUser);

	Coupon findByCode(String code);

	Coupon findById(long id);
	List<Coupon> findAll();

	Coupon findById(Long id);

	Coupon create(Coupon Coupons);

	Coupon update(Coupon Coupons);

	void delete(Long id);

	List<Coupon> findAllByUserEmail(String email);
}
