package edu.home.controller;

import edu.home.entity.Customer;
import edu.home.entity.Order;
import edu.home.entity.OrderDetail;
import edu.home.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "order")
public class OrderController {
    @Autowired
    private CouponService couponService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerPhoneAddressService addressService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(value = "checkout")
    public String checkout(Model model, HttpServletRequest request){

        model.addAttribute("address", addressService.findByCustomerEmail(request.getRemoteUser()));
        return "order/checkout";
    }

    @RequestMapping(value = "list")
    public String orderList(Model model, HttpServletRequest request) {
        List<Order> orders = orderService.findAllByCustomerEmail(request.getRemoteUser());
        List<OrderDetail> orderDetails = orderDetailService.findAll();

        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("items", orders);
        return "order/list";
    }

    @RequestMapping(value = "detail/{id}")
    public String detailOrder(Model model, @PathVariable("id") Long id) {
        Order order = orderService.findById(id);
        List<OrderDetail> orderDetails = orderDetailService.findByOrderId(id);
        Double amount = orderDetails.stream().mapToDouble(item -> item.getQuantity() * item.getNewPrice()).sum();

        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("address", addressService.findById(order.getCustomerPhoneAddress().getId()));
        model.addAttribute("amount", amount);
        model.addAttribute("coupon", couponService.findById(order.getCoupon().getId()));
        return "order/detail";
    }
}
