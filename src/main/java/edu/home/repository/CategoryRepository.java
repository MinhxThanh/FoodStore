package edu.home.repository;

import edu.home.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  @Query("select c from Category c join CategoryFood cf on cf.category.id = c.id " +
            "join Food f on f.id = cf.food.id where f.id = ?1")
    List<Category> findAllCategoriesByFoodId(Long id);
}
