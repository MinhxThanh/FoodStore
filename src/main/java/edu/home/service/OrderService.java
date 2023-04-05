package edu.home.service;

import com.fasterxml.jackson.databind.JsonNode;
import edu.home.entity.Order;

import java.util.List;

public interface OrderService {
    Order create(JsonNode orderJsonData);

    List<Order> findAllByCustomerEmail(String remoteUser);

    Order findById(Long id);
}
