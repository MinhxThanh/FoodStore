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
    
//  Giàu
  @Query("select c from Category c join CategoryFood cf on cf.category.id = c.id " +
          "join Food f on f.id = cf.food.id where f.id = ?1")
  List<Category> findAllCategoriesByFoodId(Long id);

    @Query("select c from Category c where c.id < 12 order by c.id DESC")
    List<Category> findTop11();
}