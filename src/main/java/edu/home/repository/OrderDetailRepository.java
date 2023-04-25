package edu.home.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.home.common.entity.Report;
import edu.home.common.entity.ReportMonth2;
//import edu.home.common.entity.OrderDetailResponse;
import edu.home.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	List<OrderDetail> findOrderDetailsByOrderId(Long id);

	//report1
	//doanh thu theo tháng
	@Query("SELECT new ReportMonth2" 
			+ "(MONTH(o.order.orderDate)," 
			+ "YEAR(o.order.orderDate),"
			+ "sum(o.newPrice*o.quantity))"  
			+ " FROM OrderDetail o "
			+ " WHERE o.order.status = 5" 
			+ " GROUP BY MONTH (o.order.orderDate),YEAR(o.order.orderDate)"
			+ " ORDER BY YEAR(o.order.orderDate) ASC ")
	List<ReportMonth2> getByMonth();
	
	//report2
	//số lượng khách hàng-tổng số đơn h
	@Query("SELECT new Report(o.customer.fullname" + ",sum(d.newPrice*d.quantity)," + "count(DISTINCT o)) "
			+ " FROM Order o join o.orderDetails d" + " GROUP BY o.customer.fullname"
			+ " ORDER BY sum(d.newPrice*d.quantity) DESC")
	List<Report> getInventoryByCustomer();

	// doanh thu theo loai san pham
	@Query("SELECT new Report(o.category.name,sum(d.newPrice*d.quantity),sum( d.quantity)) "
			+ " FROM CategoryFood o join  o.food.orderDetails d"
			+ " GROUP BY o.category.name" + " ORDER BY sum(d.newPrice*d.quantity) DESC")
	List<Report> getInventoryByParentCategory();

	//doanh thu theo loại sản phẩm
	@Query("SELECT new Report(o.category.name,sum(d.newPrice*d.quantity),sum( d.quantity)) "
			+ " FROM CategoryFood o join  o.food.orderDetails d "

			+ " WHERE o.category.name like '%' || :name || '%' and  MONTH(d.order.orderDate)=:month and YEAR(d.order.orderDate)=:year "
			+ " GROUP BY  o.category.name")
	List<Report> getInventoryCategoryByMonthAndByCategoryName(@Param("name") String name, @Param("month") Integer month,
			@Param("year") Integer year);


	// san pham ban nhieu nhat + "WHERE o.order.orderStatus IN('FINISHED','RATED')"
	@Query("SELECT new Report(o.food.name,sum(o.newPrice*o.quantity),o.food.id) " + " FROM OrderDetail o "
			+ " GROUP BY  o.food.name,o.food.id" + " ORDER BY sum(o.newPrice*o.quantity) DESC")
	List<Report> getInventoryProduct();


	@Query("SELECT new Report(o.food.name,sum(o.newPrice*o.quantity),count(o)) " + " FROM OrderDetail o "
			+ " WHERE o.food.id=:id and  MONTH(o.order.orderDate)=:month and YEAR(o.order.orderDate)=:year "
			+ " GROUP BY  o.food.name")
	List<Report> getInventoryProductByMonthAndByProductName(@Param("id") Long id, @Param("month") Integer month,
			@Param("year") Integer year);
	
	List<OrderDetail> getAllByOrderId(Long orderId);

	@Query(value="select o.id, f.name, (select top 1 i.image_name from image_food i WHERE i.food_id = o.food_id) as image, \n" +
			"o.quantity, o.new_price, o.old_price from [order_details] o\n" +
			"left join foods f on f.id = o.food_id\n" +
			"WHERE o.order_id = : orderId", nativeQuery = true)
	List<Tuple> getAllOrderDetailResponseByOrderId(@Param("orderId") Long orderId);
}