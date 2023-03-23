package edu.home.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.home.common.entity.CartItem;
import edu.home.entity.Cart;
import edu.home.repository.CartRepository;
import edu.home.service.CartService;
import edu.home.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository dao;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CustomerService customerService;

    @Override
    public List<Cart> create(JsonNode orderJsonData) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Cart>> type = new TypeReference<List<Cart>>(){};
        List<Cart> details = mapper.convertValue(orderJsonData.get("cartInfo"), type)
                .stream().peek(data -> data.setCustomer(customerService.findByEmailKey(request.getRemoteUser()))).collect(Collectors.toList());
        details.forEach(item ->{
            Cart cart = dao.findCartByCustomerEmailAndFoodId(item.getCustomer().getEmail(), item.getFood().getId());
            if (cart == null)
                dao.save(item);
            else
                dao.updateCartQuantityByCustomerEmailAndFoodId(item.getQuantity(), item.getCustomer().getEmail(), item.getFood().getId());
        });

        return details;
    }

    @Override
    public List<Cart> getAllByCustomerEmail(String remoteUser) {
        return dao.findAllByCustomerEmail(remoteUser);
    }

    @Override
    public void deleteCartByCustomerEmailAndFoodId(String remoteUser, Long foodId) {
        dao.deleteByCustomerEmailAndFoodId(remoteUser, foodId);
    }
}
