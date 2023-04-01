package edu.home.repository;
import org.springframework.data.domain.Pageable;
import edu.home.entity.Blog;
import edu.home.entity.CategoryBlog;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<Blog, Long> {
	
//	Page<Blog> findByNameContains(String keyword, PageRequest of);
		
	@Query("select b from Blog b left join CategoryBlog c on c.blog.id = b.id where c.category.id = ?1")
	List<Blog> findBLogsByCategory(Long categoryId);
	
	@Query("select b from Blog b where b.title LIKE %?1% ")
//			+ " OR c.category.id = 1 ", nativeQuery = true)
	Page<Blog> searchBlogs(String keyword, Pageable pageable);
	
	@Transactional 
    @Modifying
	@Query("update Blog b SET b.viewCount =?1 where b.id = ?2 ")
	 void updateViewCount(Long viewCount, Long id);
	
	@Query(value="select TOP 3 * from blogs b  ORDER BY b.view_count DESC",nativeQuery = true )
	List<Blog> topBaBlog();




	
	
	
}