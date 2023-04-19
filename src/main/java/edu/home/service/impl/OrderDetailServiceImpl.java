package edu.home.service.impl;

import edu.home.common.entity.OrderDetailResponse;
import edu.home.entity.OrderDetail;
import edu.home.repository.OrderDetailRepository;
import edu.home.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository dao;

    @Override
    public List<OrderDetail> findAll() {
        return dao.findAll();
    }

    @Override
    public List<OrderDetail> findByOrderId(Long id) {
        return dao.findOrderDetailsByOrderId(id);
    }

    @Override
    public List<OrderDetail> getAllByOrderId(Long orderId) {
        return dao.getAllByOrderId(orderId);
    }

    @Override
    public List<OrderDetailResponse> getAllOrderDetailResponseByOrderId(Long orderId) {
        List<Tuple> tuples = dao.getAllOrderDetailResponseByOrderId(orderId);
        List<OrderDetailResponse> list = tuples.stream().map(item -> new OrderDetailResponse(
                item.get(0, Long.class),
                item.get(1, String.class),
                item.get(2, String.class),
                item.get(3, Long.class),
                item.get(4, Double.class),
                item.get(5, Double.class)
        )).collect(Collectors.toList());
        return list;
    }
}
