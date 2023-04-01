package edu.home.service;

import java.math.BigInteger;
import java.util.List;

import edu.home.common.entity.*;
import edu.home.entity.Food;
import edu.home.entity.User;

public interface FoodService {

    Food findById(Long id);

    List<ListFood> getListFood();

    FoodDetail getInfoDetailByFoodId(Long food_id);

    List<ListFoodSale> getListSaleFood();

    List<ListTopNewFood> getListTopNewFood();

    List<ListFoodByCategory> getListFoodByCategoryId(BigInteger categoryId);
    
//    Giau
    List<ProcedureFoods> procedureFoods();
    Food create(Food food);
    Food update(Food food);
    void delete(Long id);
    void updateIsDisplayById(Boolean display, Long id);
    List<Food> findAll();
}
