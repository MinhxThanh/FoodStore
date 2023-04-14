package edu.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.home.entity.CustomerCoupon;
import edu.home.repository.CustomerCouponRepository;
import edu.home.service.CustomerCouponService;

@Service
public class CustomerCouponServiceImpl implements CustomerCouponService{
	@Autowired
	CustomerCouponRepository dao;

	@Override
	public List<CustomerCoupon> findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public List<CustomerCoupon> findByCouponId(Long code) {
		return dao.findByCouponId(code);
	}

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public CustomerCoupon save(CustomerCoupon customerCoupon) {
		return dao.save(customerCoupon);
	}

	@Override
	public List<CustomerCoupon> findAll() {
		return dao.findAll();
	}

	@Override
	public void customerCouponService(String emailCustomer, Long idCoupon) {
		dao.deleteByEmailCustomerAndIdCoupon(emailCustomer,idCoupon);
	}
}
