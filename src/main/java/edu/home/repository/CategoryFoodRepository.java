package edu.home.repository;

import edu.home.entity.CategoryFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryFoodRepository extends JpaRepository<CategoryFood, Long> {
}