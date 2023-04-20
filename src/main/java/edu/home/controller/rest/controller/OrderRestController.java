package edu.home.controller.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import edu.home.entity.Order;
import edu.home.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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
    
    @GetMapping("/findAll")
	public List<Order> findAll(){
		return orderService.findAll();
	}

	@GetMapping("findAllByUserEmail/{email}")
	public ResponseEntity<?> findAllByUserEmail(@PathVariable("email") String email){
		try {
			return ResponseEntity.ok(orderService.findAllByUserEmail(email));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/findStatusById/{id}")
	public Long findStatusById(@PathVariable("id") Long id) {
		return orderService.findStatusById(id);
	}
	@GetMapping("/findById/{id}")
	public Order findById(@PathVariable("id") Long id) {
		return orderService.findById(id);
	}
	@GetMapping("/findByPaymentmethodId/{id}")
	public List<Order> findByPaymentmethodId(@PathVariable("id") Long id) {
		return orderService.findByPaymentmethodId(id);
	}
	@GetMapping("/findByOrderDate/{orderDate}")
	public List<Order> findByOrderDate(@PathVariable("orderDate") Date orderDate) {
		return orderService.findByOrderDate(orderDate);
	}
	@GetMapping("/findByShippedDate/{shippedDate}")
	public List<Order> findByShippedDate(@PathVariable("shippedDate") Date shippedDate) {
		return orderService.findByShippedDate(shippedDate);
	}
	@PutMapping("/status/{status}/{id}")
	public void updateStatusById(@PathVariable("status") Long status, @PathVariable("id") Long id) {
		orderService.updateStatusById(status, id);
	}

	@DeleteMapping("cancelOrder/{orderId}")
	public void cancelOrder(@PathVariable("orderId") Long orderId) {
		try {
			orderService.cancelOrderByOrderId(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
