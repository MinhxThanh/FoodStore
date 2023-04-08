package edu.home.repository;

import edu.home.entity.CategoryBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryBlogRepository extends JpaRepository<CategoryBlog, Long> {

    @Transactional
    @Modifying
    @Query("delete from CategoryBlog c where c.blog.id = ?1 and  c.category.id = ?2")
    void deleteByBlogIdAndCategoryId(Long blogId, Long categoryId);

    List<CategoryBlog> findAllByBlogId(Long id);
}
