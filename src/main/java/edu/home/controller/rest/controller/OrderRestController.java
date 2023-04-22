package edu.home.controller.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import edu.home.common.entity.MailInfoOrder;
import edu.home.common.entity.OrderDetail;
import edu.home.entity.Order;
import edu.home.service.MailerService;
import edu.home.service.OrderService;
import edu.home.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/order")
public class OrderRestController {
    @Autowired
    private OrderService orderService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private MailerService mailerService;

    @PostMapping(value = "create")
    public ResponseEntity<?> create(@RequestBody JsonNode orderJsonData) throws MessagingException {
        try {
            Order order = orderService.create(orderJsonData);
			MailInfoOrder mail = new MailInfoOrder();
			mail.setTo(order.getCustomer().getEmail());
			mail.setSubject("SunFood received the order #" + order.getId());
			mail.setOrder(order);
			mailerService.sendMailOrder(mail);

            return ResponseEntity.ok(order);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

	@PostMapping(value = "createPaypal")
	public ResponseEntity<?> createPaypal(@RequestBody JsonNode orderJsonData) throws MessagingException {
		try {
			Order order = orderService.createPaypal(orderJsonData);

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProductName("Order Id: #" + order.getId() +
					"\n Order By: " + order.getCustomer().getLastName() + " " + order.getCustomer().getFirstName() + " Email: " + order.getCustomer().getEmail() +
					"\n Order Date: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(order.getOrderDate()));
			double money = order.getPrice() / 23495.0;
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setDecimalSeparator('.');
			DecimalFormat df = new DecimalFormat("0.00", symbols);
			String price = df.format(money);
			System.out.println("Price: " + price);
			orderDetail.setSubtotal(price);
			orderDetail.setShipping(String.valueOf(0));
			orderDetail.setTax(String.valueOf(0));
			orderDetail.setTotal(price);

			String approvalLink = paymentService.authorizePayment(orderDetail);

			MailInfoOrder mail = new MailInfoOrder();
			mail.setTo(order.getCustomer().getEmail());
			mail.setSubject("SunFood received the order #" + order.getId());
			mail.setOrder(order);
			mailerService.sendMailOrder(mail);

			System.out.println("AuthorizePaymentController: " + approvalLink);
			order.setImage(approvalLink);
			return ResponseEntity.ok(order);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(value = "findAllOrderByUserEmail/{email}")
	public ResponseEntity<?> findAllOrderByUserEmail(@PathVariable("email") String email) {
		try {
			return ResponseEntity.ok(orderService.findAllOrderByUserEmail(email));
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
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
