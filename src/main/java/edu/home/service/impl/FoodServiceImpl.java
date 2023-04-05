package edu.home.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.home.common.entity.FoodDetail;
import edu.home.common.entity.ListFood;
import edu.home.common.entity.ListFoodByCategory;
import edu.home.common.entity.ListFoodSale;
import edu.home.common.entity.ListTopNewFood;
import edu.home.entity.Food;
import edu.home.repository.FoodRepository;
import edu.home.service.FoodService;
import edu.home.store.Convert;

@Service
public class FoodServiceImpl implements FoodService{
	 @Autowired
	 private FoodRepository dao;

	@Override
	public Food findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public List<ListFood> getListFood() {
	 List<Tuple> list = dao.getListFood();
	 List<ListFood> listFoods = list.stream().map(item -> new ListFood(
			 item.get(0, BigInteger.class),
			 item.get(1, String.class),
			 item.get(2, Double.class),
			 item.get(3, Double.class),
			 item.get(4, Double.class),
			 item.get(5, Integer.class),
			 item.get(6, String.class),
			 item.get(7, String.class)
	 )).collect(Collectors.toList());
		return listFoods;
	}

	@Override
	public FoodDetail getInfoDetailByFoodId(Long food_id) {
	 List<Tuple> list = dao.getInfoDetailByFoodId(food_id);
	 FoodDetail foodDetail = list.stream().map(item -> new FoodDetail(
			 item.get(0, BigInteger.class),
			 item.get(1, String.class),
			 item.get(2, Double.class),
			 item.get(3, Double.class),
			 item.get(4, BigInteger.class),
			 item.get(5, BigInteger.class),
			 item.get(6, BigInteger.class),
			 item.get(7, String.class),
			 item.get(8, String.class),
			 item.get(9, Integer.class),
			 item.get(10, Integer.class),
			 item.get(11, String.class),
			 item.get(12, String.class),
			 item.get(13, BigInteger.class)
	 )).collect(Collectors.toList()).get(0);
		return foodDetail;
	}

	@Override
	public List<ListFoodSale> getListSaleFood() {
	 List<Tuple> list = dao.getListSaleFood();
	 List<ListFoodSale> foodSales = list.stream().map(item -> new ListFoodSale(
			 item.get(0, BigInteger.class),
			 item.get(1, String.class),
			 item.get(2, Double.class),
			 item.get(3, Double.class),
			 item.get(4, Double.class),
			 item.get(5, String.class),
			 item.get(6, String.class)
	 )).collect(Collectors.toList());
		return foodSales;
	}

	@Override
	public List<ListTopNewFood> getListTopNewFood() {
	 List<Tuple> list = dao.getListTopNewFood();
	 List<ListTopNewFood> foodList = list.stream().map(item -> new ListTopNewFood(
			 item.get(0, BigInteger.class),
			 item.get(1, String.class),
			 item.get(2, Double.class),
			 item.get(3, String.class)
	 )).collect(Collectors.toList());
		return foodList;
	}

	@Override
	public List<ListFoodByCategory> getListFoodByCategoryId(BigInteger categoryId) {
		List<Tuple> list = dao.getListFoodByCategoryId(categoryId);
		List<ListFoodByCategory> listFoods = list.stream().map(item -> new ListFoodByCategory(
				item.get(0, BigInteger.class),
				item.get(1, String.class),
				item.get(2, Double.class),
				item.get(3, Double.class),
				item.get(4, Double.class),
				item.get(5, Integer.class),
				item.get(6, String.class),
				item.get(7, String.class)
		)).collect(Collectors.toList());
		return listFoods;
	}
	

	@Override
	public Page<Food> getByFilter(String keyword, Optional<Double> priceMin, Optional<Double> priceMax,
			Optional<Integer> quantity, Optional<Integer> view, Optional<Long> createDate,
			Optional<Boolean> isDisplay, Optional<Long> category_id, Pageable pageable) {
		List<Food> list = getByKeywordEng(keyword,pageable);
//		if(priceMin.isPresent() && priceMin.get()>=0) list = list.stream().filter(o-> o.getPrice() >= priceMin.get()).toList();
//		if(priceMax.isPresent() && priceMax.get()>=0 ) list = list.stream().filter(o-> o.getPrice() <= priceMax.get()).toList();
//		if(quantity.isPresent()) list = list.stream().filter(o-> o.getQuantityLimit() >= quantity.get()).toList();
//		if(view.isPresent()) list = list.stream().filter(o-> o.getViewCount() >= view.get()).toList();
//		if(createDate.isPresent()) list = list.stream().filter(o-> o.getCreateDate().getTime() >=  createDate.get()).toList();
//		if(isDisplay.isPresent()) list = list.stream().filter(o-> o.isDisplay() == isDisplay.get()).toList();
//		if(category_id.isPresent()) list = list.stream().filter(o-> o.getCategoryFoods().stream().anyMatch(c -> c.getCategory().getId() == category_id.get()) ).toList();
		return (Page<Food>) Convert.toPage(list, pageable);
	}
	
	@Override
	public List<Food> getByKeywordEng(String keyword,Pageable pageable) {
		List<Food> list = dao.findAll(pageable.getSort());
//		list = list.stream()
//				.filter(o -> Convert.toEngString(o.getName().toLowerCase()).contains(Convert.toEngString(keyword.toLowerCase()))
//				|| Convert.toEngString(o.getDescription().toLowerCase()).contains(Convert.toEngString(keyword.toLowerCase()))).toList();
		return list;
	}
}
