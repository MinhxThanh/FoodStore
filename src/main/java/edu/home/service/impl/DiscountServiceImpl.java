package edu.home.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

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

	@Override
	public List<Discount> findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public Discount save(Discount discount) {
		return dao.save(discount);
	}

	@Override
	public List<Discount> findByCreateDate(Date createDate) {
		return dao.findByCreateDate(createDate);
	}

	@Override
	public List<Discount> findByStartDate(Date startDate) {
		return dao.findByStartDate(startDate);
	}

	@Override
	public List<Discount> findByEndDate(Date endDate) {
		return dao.findByEndDate(endDate);
	}

	@Override
	public List<Object> FindAllFoodDiscount(String discountName) {
		return dao.FindAllFoodDiscount(discountName);
	}

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public void deleteByNameAndFoodId(String name, Long foodId) {

	}

	@Override
	public List<Discount> findAllByUserEmail(String email) {
		return dao.findAllByUserEmail(email);
	}

}
