package edu.home.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import edu.home.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomerEmail(String remoteUser);
    
//    Giàu
    @Query("select o.status from Order o where o.id= ?1")
	public Long findStatusById(Long id);

	@Transactional
	@Modifying
	@Query("update Order o set o.status = ?1 where o.id = ?2")
	void updateStatusById(Long status, Long id);
	
	@Query("select o from Order o ")
	List<Order> findPaymentmethodByPaymentmethodId();

	@Query("select o from Order o where o.paymentmethod.id = ?1")
	public List<Order> findByPaymentmethodId(Long id);

	@Query("select o from Order o where o.orderDate = ?1")
	public List<Order> findByOrderDate(Date orderDate);

	@Query("select o from Order o where o.shippedDate =?1")
	public List<Order> findByShippedDate(Date shippedDate);
}