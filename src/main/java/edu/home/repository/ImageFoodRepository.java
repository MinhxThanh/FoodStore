package edu.home.repository;

import edu.home.entity.ImageFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import javax.transaction.Transactional;

public interface ImageFoodRepository extends JpaRepository<ImageFood, Long> {
    @Query("select i from ImageFood i where i.food.id = ?1")
    List<ImageFood> searchByFoodId(Long foodId);
    
    @Query("select i from ImageFood i where i.food.id =?1")
	List<ImageFood> findByFoodId(Long id);

    @Transactional
	@Modifying
    @Query("delete from ImageFood i where i.imageName =?1")
	void deleteByImageName(String imageName);
}
