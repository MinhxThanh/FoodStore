package edu.home.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.home.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c left join CategoryFood cf on cf.category.id = c.id where cf.food.id = ?1")
    List<Category> findAllByFoodId(Long foodId);    
    
    @Query("SELECT c FROM Category c WHERE c.name = ?1 ")
	Optional<Category> findByName(String keyword);
}