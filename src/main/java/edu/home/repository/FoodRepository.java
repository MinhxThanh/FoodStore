package edu.home.repository;

import edu.home.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodRepository extends JpaRepository<Food, Long> {
  @Query(value="{CALL getBasicFoodByCategoryId(:id)}", nativeQuery = true)
  List<Tuple> procedure_name(@Param("id") Integer id);

  @Query(value="{CALL getListProduct()}", nativeQuery = true)
    List<Tuple> getListFood();
  
  @Query(value="{call procedure_foods}", nativeQuery=true)
	List<Tuple> procedureFoods();
	
	@Query(value = "select * from getAllFoodImageFunc()", nativeQuery = true)
    List<Tuple> listFood();

    @Query(value = "{call getAllFoodImageByCategory(:categoryName)}", nativeQuery = true)
    List<Tuple> findAllFoodByCategoryName(@Param("categoryName") String categoryName);
    
    @Query("select f from Food f where f.id =?1")
    Food findFoodByIdFood (Long id);
  
}
