package edu.home.repository;

import edu.home.entity.ImageFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFoodRepository extends JpaRepository<ImageFood, Long> {
}