package edu.home.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.home.entity.Food;

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
  
	//Giau
	@Query(value="{call procedure_foods}", nativeQuery=true)
		List<Tuple> procedureFoods();
	//Giàu
	@Transactional
	@Modifying
	@Query("update Food f set f.isDisplay = ?1 where f.id = ?2")
	void updateIsDisplayById(Boolean display, Long id);
	
	@Query("select f from Food f where f.createDate = ?1")
	List<Food> findByCreateDate(Date createDate);
	
}
