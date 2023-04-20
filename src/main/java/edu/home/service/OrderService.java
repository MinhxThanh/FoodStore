package edu.home.service;

import com.fasterxml.jackson.databind.JsonNode;
import edu.home.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order create(JsonNode orderJsonData);

    List<Order> findAllByCustomerEmail(String remoteUser);

    Order findById(Long id);
    
//    Giàu
    List<Order> findAll();
	Long findStatusById(Long id);
	void updateStatusById(Long status, Long id);
	List<Order> findByPaymentmethodId(Long id);
	List<Order> findByOrderDate(Date orderDate);
	List<Order> findByShippedDate(Date shippedDate);

    List<Order> findAllByUserEmail(String email);

    void cancelOrderByOrderId(Long orderId);
}
