package edu.home.repository;

import edu.home.entity.Discount;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
	@Query("select d from Discount d where d.name = ?1")
	List<Discount> findByName(String name);
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

	@Query("select d from Discount d where d.createDate = ?1")
	List<Discount> findByCreateDate(Date createDate);

	@Query("select d from Discount d where d.startDate = ?1")
	List<Discount> findByStartDate(Date startDate);

	@Query("select d from Discount d where d.endDate = ?1")
	List<Discount> findByEndDate(Date endDate);
	
	@Query("select f.id from Food f join Discount d on f.id=d.food.id where d.name like %:discountName%")
	List<Object> FindAllFoodDiscount(@Param("discountName") String discountName);

    List<Discount> findAllByUserEmail(String email);
}