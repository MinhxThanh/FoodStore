package edu.home.service;

import com.fasterxml.jackson.databind.JsonNode;
import edu.home.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> create(JsonNode orderJsonData);

    List<Cart> getAllByCustomerEmail(String remoteUser);

    void deleteCartByCustomerEmailAndFoodId(String remoteUser, Long foodId);
}
