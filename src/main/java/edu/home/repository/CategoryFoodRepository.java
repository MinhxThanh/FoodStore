package edu.home.repository;

import edu.home.entity.CategoryFood;
import edu.home.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryFoodRepository extends JpaRepository<CategoryFood, Long> {
}