package edu.home.service.impl;

import edu.home.entity.OrderDetail;
import edu.home.repository.OrderDetailRepository;
import edu.home.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
