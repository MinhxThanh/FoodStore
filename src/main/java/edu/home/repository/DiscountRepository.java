package edu.home.repository;

import edu.home.entity.Discount;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
	@Query("select d from Discount d where d.name = ?1")
	List<Discount> findByName(String name);
	
	//Giàu
	@Transactional
    @Modifying
	@Query("update Discount d set d.isDisplay = ?1 where d.id = ?2")
	void updateIsDisplayById(Boolean display, Long id);

	@Transactional
    @Modifying
	@Query("update Discount d set d.isFixed = ?1 where d.id = ?2")
	void updateIsFixedById(Boolean display, Long id);

	@Query("select d from Discount d where d.food.id = ?1")
	List<Discount> findByFoodId(Long id);

	@Transactional
    @Modifying
	@Query("update Discount d set d.food.id= ?1 where d.id = ?2")
	void updateFoodIdById(Long foodId, Long discountId);
}