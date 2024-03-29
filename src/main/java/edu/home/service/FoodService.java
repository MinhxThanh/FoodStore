package edu.home.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.home.common.entity.FoodDetail;
import edu.home.common.entity.ListFood;
import edu.home.common.entity.ListFoodByCategory;
import edu.home.common.entity.ListFoodSale;
import edu.home.common.entity.ListTopNewFood;
import edu.home.common.entity.ProcedureFoods;
import edu.home.entity.Food;
import edu.home.entity.ImageFood;

public interface FoodService {

    Food findById(Long id);

    List<ListFood> getListFood();

    FoodDetail getInfoDetailByFoodId(Long food_id);

    List<ListFoodSale> getListSaleFood();

    List<ListTopNewFood> getListTopNewFood();

    List<ListFoodByCategory> getListFoodByCategoryId(BigInteger categoryId);
	
	List<ProcedureFoods> procedureFoods();
    Food create(Food food);
    Food update(Food food);
    void delete(Long id);
    void updateIsDisplayById(Boolean display, Long id);
    List<Food> findAll();
	List<Food> findByCreateDate(Date createDate);

    List<ListFoodByCategory> getTop2FoodByCategoryId(BigInteger categoryId);

    void updateViewCountById(Long i, Long foodId);

    List<Food> findAllByUserEmail(String email);

    List<ListFood> getListFoodByUserId(long id);

    List<ListTopNewFood> getTopRatedProducts();

    List<ListTopNewFood> getReviewProducts();
}
