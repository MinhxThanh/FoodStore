package edu.home.repository;

import edu.home.entity.CategoryFood;
import edu.home.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import edu.home.common.entity.FoodByCategoryId;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface CategoryFoodRepository extends JpaRepository<CategoryFood, Long> {
  @Query("select new FoodByCategoryId (f.id,f.name,f.price,f.description) from Food f join CategoryFood c on c.food.id = f.id where c.category.id=?1")
	public List<FoodByCategoryId> findByCategoryId(Long id);
	
	CategoryFood findByCategory(Integer id);
	
	@Query("select c from CategoryFood c where c.food.id = ?1")
    List<CategoryFood> findByFoodAndId(long id);

    @Query("select c from CategoryFood c where c.food.id = ?1 and c.category.id = ?2")
    CategoryFood findByFoodIdAndCategoryId(Integer productID, Integer categoryId);

    @Transactional
    @Modifying
    @Query("delete from CategoryFood c where c.category.id = ?1 and c.food.id = ?2")
    Integer deleteCategoryFoodByCategory_IdAndFood_Id(long cateID, long pId);

    @Query("select c from CategoryFood c where c.food.id = ?1")
    List<CategoryFood> findByFoodId(Long id);
}
