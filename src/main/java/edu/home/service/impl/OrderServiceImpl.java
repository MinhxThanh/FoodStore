package edu.home.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.home.entity.Order;
import edu.home.entity.OrderDetail;
import edu.home.repository.OrderDetailRepository;
import edu.home.repository.OrderRepository;
import edu.home.service.CustomerPhoneAddressService;
import edu.home.service.CustomerService;
import edu.home.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository dao;
    @Autowired
    private OrderDetailRepository detailRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerPhoneAddressService addressService;
    @Autowired
    private HttpServletRequest request;

    @Override
    public Order create(JsonNode orderJsonData) {

        ObjectMapper mapper = new ObjectMapper();

        Order order = mapper.convertValue(orderJsonData, Order.class);
        order.setCustomer(customerService.findByEmailKey(request.getRemoteUser()));
        dao.save(order);

        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>(){};
        List<OrderDetail> details = mapper.convertValue(orderJsonData.get("orderDetails"), type)
                .stream().peek(data -> data.setOrder(order)).collect(Collectors.toList());
        detailRepository.saveAll(details);

        return order;
    }

    @Override
    public List<Order> findAllByCustomerEmail(String remoteUser) {
        return dao.findAllByCustomerEmail(remoteUser);
    }

    @Override
    public Order findById(Long id) {
        return dao.findById(id).get();
    }
}
