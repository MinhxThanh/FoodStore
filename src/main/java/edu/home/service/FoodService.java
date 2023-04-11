package edu.home.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.home.common.entity.FoodDetail;
import edu.home.common.entity.ListFood;
import edu.home.common.entity.ListFoodByCategory;
import edu.home.common.entity.ListFoodSale;
import edu.home.common.entity.ListTopNewFood;
import edu.home.common.entity.ProcedureFoods;
import edu.home.entity.Food;

public interface FoodService {

    Food findById(Long id);

    List<ListFood> getListFood();

    FoodDetail getInfoDetailByFoodId(Long food_id);

    List<ListFoodSale> getListSaleFood();

    List<ListTopNewFood> getListTopNewFood();

    List<ListFoodByCategory> getListFoodByCategoryId(BigInteger categoryId);
    
    Page<Food> getByFilter( String keyword , Optional<Double> priceMin , Optional<Double> priceMax , Optional<Integer> quantity , Optional<Integer> view , Optional<Long> createDate, Optional<Boolean> isDisplay , Optional<Long> category_id ,Pageable pageable);

	List<Food> getByKeywordEng(String keyword, Pageable pageable);
	
	List<ProcedureFoods> procedureFoods();
    Food create(Food food);
    Food update(Food food);
    void delete(Long id);
    void updateIsDisplayById(Boolean display, Long id);
    List<Food> findAll();
	List<Food> findByCreateDate(Date createDate);
}
