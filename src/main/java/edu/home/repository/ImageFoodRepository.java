package edu.home.repository;

import edu.home.entity.ImageFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageFoodRepository extends JpaRepository<ImageFood, Long> {
    @Query("select i from ImageFood i where i.food.id = ?1")
    List<ImageFood> searchByFoodId(Long foodId);
    
}
