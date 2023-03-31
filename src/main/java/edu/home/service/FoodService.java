package edu.home.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.home.common.entity.*;
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
}
