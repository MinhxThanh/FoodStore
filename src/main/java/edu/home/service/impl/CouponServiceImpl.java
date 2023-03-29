package edu.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.home.entity.Coupon;
import edu.home.repository.CouponRepository;
import edu.home.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository dao;

	@Override
	public List<Coupon> findAll() {
		return dao.findAll();
	}

	@Override
	public Coupon findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public Coupon create(Coupon Coupons) {
		return dao.save(Coupons);
	}

	@Override
	public Coupon update(Coupon Coupons) {
		return dao.save(Coupons);
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}
}
