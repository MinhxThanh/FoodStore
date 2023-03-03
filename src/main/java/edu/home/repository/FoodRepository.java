package edu.home.repository;

import edu.home.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
  @Query(value="{CALL getBasicFoodByCategoryId(:id)}", nativeQuery = true)
  List<Tuple> procedure_name(@Param("id") Integer id);

  @Query(value="{CALL getListProduct()}", nativeQuery = true)
    List<Tuple> getListFood();
}
