package edu.home.controller.rest.controller;

import edu.home.common.entity.OrderDetailResponse;
import edu.home.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/orderDetail")
public class OrderDetailRestController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping(value = "getAllByOrderId/{orderId}")
    public ResponseEntity<?> getAllByOrderId(@PathVariable("orderId") Long orderId){
        try {
//            List<OrderDetailResponse> list = orderDetailService.getAllOrderDetailResponseByOrderId(orderId);
//            list.forEach(item ->{
//                System.out.println("Item: " + item.getName());
//            });
            return ResponseEntity.ok(orderDetailService.getAllByOrderId(orderId));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}
