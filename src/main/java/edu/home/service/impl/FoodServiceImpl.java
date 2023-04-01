package edu.home.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import edu.home.common.entity.*;
import edu.home.entity.Food;
import edu.home.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.home.repository.FoodRepository;
import edu.home.service.FoodService;

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
	
//	Giau
	@Override
	public List<ProcedureFoods> procedureFoods() {
		List<Tuple> ls = dao.procedureFoods();
		List<ProcedureFoods> lsItem = ls.stream().map(f -> new ProcedureFoods(
				f.get(0, BigInteger.class),
				f.get(1, String.class),
				f.get(2, Double.class),
				f.get(3, BigInteger.class),
				f.get(4, BigInteger.class),
				f.get(5, BigInteger.class),
				f.get(6, String.class),
				f.get(7, Date.class),
				f.get(8, BigInteger.class),
				f.get(9, Boolean.class),
				f.get(10, String.class),
				f.get(11, String.class),
				f.get(12, String.class)
				
				)).collect(Collectors.toList());
		return lsItem;
	}
	
//	Giàu
	@Override
	public Food create(Food food) {
	    return dao.save(food);
	}
//	Giàu
	@Override
	public Food update(Food food) {
	    return dao.save(food);
	}
//	Giàu
	@Override
	public void delete(Long id) {
	    dao.deleteById(id);
	}

	@Override
	public void updateIsDisplayById(Boolean display, Long id) {
		dao.updateIsDisplayById(display, id);
	}

	@Override
	public List<Food> findAll() {
		return dao.findAll();
	}
}
