package edu.home.service;

import java.util.List;

import edu.home.entity.Discount;

public interface DiscountService {
	//Giàu
	Discount findById(Long id);
	Discount update(Discount discount);
	void updateIsDisplayById(Boolean display, Long id);
	List<Discount> findAll();
	void updateIsFixedById(Boolean display, Long id);
	List<Discount> findByFoodId(Long id);
	void updateFoodIdById(Long foodId, Long discountId);
}
