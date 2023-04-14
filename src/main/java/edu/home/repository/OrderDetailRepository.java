package edu.home.repository;
import java.util.List;

import edu.home.entity.OrderDetail;
import edu.home.entity.Report;
import edu.home.entity.ReportMonth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import edu.home.entity.OrderDetail;
import edu.home.entity.Report;
import edu.home.entity.ReportMonth;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findOrderDetailsByOrderId(Long id);
    
//	 + " WHERE o.order.orderStatus  IN('FINISHED','RATED')"
    // doanh thu theo loai san pham
    @Query("SELECT new Report(o.category.name,sum(d.newPrice*d.quantity),sum( d.quantity)) "
 			+ " FROM CategoryFood o join  o.food.orderDetails d"
// 			+ " WHERE o.product.category.parentCategory.id =:parentId AND MONTH(o.order.createDate) like '%' || :date || '%' AND o.order.orderStatus  IN('FINISHED','RATED')"
 			+ " GROUP BY o.category.name" + " ORDER BY sum(d.newPrice*d.quantity) DESC")
 	List<Report> getInventoryByParentCategory();
   
	@Query("SELECT new Report(o.category.name,sum(d.newPrice*d.quantity),sum( d.quantity)) "
			+ " FROM CategoryFood o join  o.food.orderDetails d "
			
			+ " WHERE o.category.name like :name and  MONTH(d.order.orderDate)=:month and YEAR(d.order.orderDate)=:year "
	+ " GROUP BY  o.category.name")
	List<Report> getInventoryCategoryByMonthAndByCategoryName(@Param("name") String name, @Param("month") Integer month,
			@Param("year") Integer year);
	// doanh thu theo thang
	@Query("SELECT new ReportMonth" + "(MONTH(o.order.orderDate)," + "YEAR(o.order.orderDate),"
			+ "sum(o.newPrice*o.quantity)," + "count(o)," + "o.order.image) " + " FROM OrderDetail o "
			+ " GROUP BY MONTH(o.order.orderDate),YEAR(o.order.orderDate),o.order.image"
			+ " ORDER BY YEAR(o.order.orderDate) ASC ")
	List<ReportMonth> getByMonth();

	// so luong don hang
	@Query("SELECT new Report(o.customer.fullname" + ",sum(d.newPrice*d.quantity)," + "count(DISTINCT o)) "
			+ " FROM Order o join o.orderDetails d" + " GROUP BY o.customer.fullname"
			+ " ORDER BY sum(d.newPrice*d.quantity) DESC")
	List<Report> getInventoryByCustomer();

	// san pham ban nhieu nhat  + "WHERE  o.order.orderStatus  IN('FINISHED','RATED')"
	@Query("SELECT new Report(o.food.name,sum(o.newPrice*o.quantity),o.food.id) "
			+ " FROM OrderDetail o " 
			+ " GROUP BY  o.food.name,o.food.id" + " ORDER BY sum(o.newPrice*o.quantity) DESC")
	List<Report> getInventoryProduct();
	
	//truyen vao 3 tham so
//		@Query("SELECT new ReportMonth(MONTH(o.order.orderDate),YEAR(o.order.orderDate),sum(o.newPrice*o.quantity),count(o),o.food.name) "
//				+ " FROM OrderDetail o "
//				+ " WHERE o.food.id=:id and  MONTH(o.order.orderDate)=:month and YEAR(o.order.orderDate)=:year")
//		List<ReportMonth>  (@Param("id") Long id, @Param("month") Integer month,
//				@Param("year") Integer year);
	@Query("SELECT new Report(o.food.name,sum(o.newPrice*o.quantity),count(o)) "
			+ " FROM OrderDetail o "
			
			+ " WHERE o.food.id=:id and  MONTH(o.order.orderDate)=:month and YEAR(o.order.orderDate)=:year "
	+ " GROUP BY  o.food.name")
	List<Report> getInventoryProductByMonthAndByProductName(@Param("id") Long id, @Param("month") Integer month,
			@Param("year") Integer year);
}