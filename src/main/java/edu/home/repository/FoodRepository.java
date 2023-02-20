package edu.home.repository;

import edu.home.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
  @Query(value="{CALL PROCEDURE_NAME(:id)}", nativeQuery = true)
	List<Tuple> procedure_name(@Param("id") Integer id);
}
