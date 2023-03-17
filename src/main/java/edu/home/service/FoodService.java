package edu.home.service;

import java.util.List;

import edu.home.common.entity.ImageNameFood;
import edu.home.common.entity.ListFood;
import edu.home.entity.Food;

public interface FoodService {
	    
    List<ImageNameFood> listfood(int id);

    Food findById(Long id);

    List<ListFood> getListFood();
	
	List<ImageNameFood> listfood(int id);
	
 List<ProcedureFoods> procedureFoods();

 Food create(Food food);

 Food update(Food food);

 void delete(Long id);

 List<FoodList> findAllFoodAndImageFromImageDescribes();

 List<FoodList> findAllFoodByCategoryName(String s);

 List<FoodList> findAllFoodAndImageFromImageDescribesFunc();
	
 Food findFoodByIdFood(Long id);
}
