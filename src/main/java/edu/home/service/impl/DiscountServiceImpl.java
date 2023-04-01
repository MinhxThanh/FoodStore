package edu.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.home.entity.Discount;
import edu.home.repository.DiscountRepository;
import edu.home.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService{
	@Autowired
	DiscountRepository dao;

	@Override
	public Discount findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public Discount update(Discount discount) {
		return dao.save(discount);
	}

	@Override
	public void updateIsDisplayById(Boolean display, Long id) {
		dao.updateIsDisplayById(display, id);
	}

	@Override
	public List<Discount> findAll() {
		return dao.findAll();
	}

	@Override
	public void updateIsFixedById(Boolean fixed, Long id) {
		dao.updateIsFixedById(fixed, id);
	}

	@Override
	public List<Discount> findByFoodId(Long id) {
		return dao.findByFoodId(id);
	}

	@Override
	public void updateFoodIdById(Long foodId, Long discountId) {
		dao.updateFoodIdById(foodId, discountId);
	}
	
}
