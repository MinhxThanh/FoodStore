package edu.home.controller.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import edu.home.entity.Order;
import edu.home.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/order")
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "create")
    public ResponseEntity<?> create(@RequestBody JsonNode orderJsonData) throws MessagingException {
        try {
            System.out.println("------");
            System.out.println("orderJsonData: " + orderJsonData);
            LocalTime currentTime = LocalTime.now();
            System.out.println("Current local time: " + currentTime);
            Order order = orderService.create(orderJsonData);
            System.out.println("order: " + order.getId());
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
