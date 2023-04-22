package edu.home.repository;

import edu.home.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.review.food.id =?1")
    List<Comment> findAllByFoodId(Long foodId);

    @Query("select c from Comment c where c.review.food.id = :foodId")
    List<Comment> getAllByFoodId(Long foodId);
}