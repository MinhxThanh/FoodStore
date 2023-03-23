package edu.home.service;

import java.util.List;
import edu.home.common.entity.FoodByCategoryId;
import edu.home.entity.CategoryFood;

public interface CategoryFoodService {
	List<FoodByCategoryId> findByCategoryId(Long i);

    List<CategoryFood> findAll();

    List<CategoryFood> findByIdFood(long id);

    Integer deleteCategoryFoodByCateIDAndFoodId(long cid, long pid);

    CategoryFood findCategoryFoodByFoodIdAndCategoryId(Integer foodId, Integer categoryId);
    CategoryFood findByFoodId(Long id);
    CategoryFood save(CategoryFood categoryFood);
}
