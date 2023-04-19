package edu.home.service;

import edu.home.common.entity.OrderDetailResponse;
import edu.home.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findAll();

    List<OrderDetail> findByOrderId(Long id);

    List<OrderDetail> getAllByOrderId(Long orderId);

    List<OrderDetailResponse> getAllOrderDetailResponseByOrderId(Long orderId);
}
