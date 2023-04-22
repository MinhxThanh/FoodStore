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
	public void customerCouponService(String emailCustomer, Long idCoupon) {
		dao.deleteByEmailCustomerAndIdCoupon(emailCustomer,idCoupon);
	}
	@Override
	public List<CustomerCoupon> findAll() {
		return dao.findAll();
	}

	@Override
	public void deleteByNameAndFoodId(String name, Long foodId) {
		dao.deleteByEmailCustomerAndIdCoupon(name, foodId);
	}

//	Giau
	@Override
	public CustomerCoupon findByCustomerEmailAndCouponId(String email, Long id) {
		return dao.findByCustomerEmailAndCouponId(email, id);
	}

	@Override
	public void updateCustomerCouponByCustomerEmailAndCouponId(String email, Long couponId) {
		dao.updateCustomerCouponByCustomerEmailAndCouponId(email, couponId);
	}

	@Override
	public CustomerCoupon create(CustomerCoupon cusCp) {
		return dao.save(cusCp);
	}
}
