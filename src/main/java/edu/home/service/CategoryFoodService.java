package edu.home.service;

import java.util.List;
import edu.home.common.entity.FoodByCategoryId;
import edu.home.entity.CategoryFood;

public interface CategoryFoodService {
	List<FoodByCategoryId> findByCategoryId(Long i);

    List<CategoryFood> findAll();

    List<CategoryFood> findByIdFood(long id);

    Integer deleteCategoryFoodByCateIDAndFoodId(long cid, long pid);
    List<CategoryFood> findByFoodId(Long id);
    
//    Giàu
    CategoryFood save(CategoryFood categoryFood);
//    Giàu
    CategoryFood findCategoryFoodByFoodIdAndCategoryId(Integer foodId, Integer categoryId);
}
