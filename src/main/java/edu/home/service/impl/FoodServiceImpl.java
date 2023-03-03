package edu.home.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import edu.home.common.entity.ListFood;
import edu.home.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.home.common.entity.ImageNameFood;
import edu.home.repository.FoodRepository;
import edu.home.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService{
 @Autowired
 private FoodRepository dao;
 
 @Override
 public List<ImageNameFood> listfood(int id){
	 List<Tuple> list = dao.procedure_name(id);
	 List<ImageNameFood> listItem = list.stream().map(t -> new ImageNameFood(
			 t.get(0, BigInteger.class),
			 t.get(1, String.class),
			 t.get(2, String.class),
			 t.get(3, Double.class),
			 t.get(4, String.class)
			 )).collect(Collectors.toList());
	 
	return listItem;
 }

	@Override
	public Food findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public List<ListFood> getListFood() {
	 List<Tuple> list = dao.getListFood();
	 List<ListFood> listFoods = list.stream().map(t -> new ListFood(
			 t.get(0, BigInteger.class),
			 t.get(1, String.class),
			 t.get(2, Double.class),
			 t.get(3, String.class)
	 )).collect(Collectors.toList());
		return listFoods;
	}
}
