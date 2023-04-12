package edu.home.service;

import java.util.Date;
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
	
	List<Discount> findByName(String name);

	Discount save(Discount discount);

	List<Discount> findByCreateDate(Date createDate);

	List<Discount> findByStartDate(Date startDate);

	List<Discount> findByEndDate(Date endDate);
	
	List<Object> FindAllFoodDiscount(String discountName);

	void deleteById(Long id);
}
