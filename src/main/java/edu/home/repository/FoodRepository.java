package edu.home.repository;

import edu.home.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
  @Query(value="{CALL sp_getListFood()}", nativeQuery = true)
  List<Tuple> getListFood();

  @Query(value="{CALL getInfoDetailFood(:id)}", nativeQuery = true)
  List<Tuple> getInfoDetailByFoodId(@Param("id") Long id);

  @Query(value="{CALL sp_getListFoodSale()}", nativeQuery = true)
  List<Tuple> getListSaleFood();

  @Query(value="{CALL sp_getTop9Food()}", nativeQuery = true)
  List<Tuple> getListTopNewFood();

  @Query(value="{CALL sp_getListFoodByCategoryId(:categoryId)}", nativeQuery = true)
  List<Tuple> getListFoodByCategoryId(@Param("categoryId") BigInteger categoryId);
}
