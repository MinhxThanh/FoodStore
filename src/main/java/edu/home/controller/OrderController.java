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
        List<Order> all = orderService.findAllByCustomerEmail(request.getRemoteUser());
        List<OrderDetail> orderDetails = orderDetailService.findAll();
//        find list order by status
        List<Order> listProcessed = orderService.findAllByCustomerEmailAndStatus(request.getRemoteUser(), (long) 1);
        List<Order> listOrderReceived = orderService.findAllByCustomerEmailAndStatus(request.getRemoteUser(), (long) 2);
        List<Order> listShipping = orderService.findAllByCustomerEmailAndStatus(request.getRemoteUser(), (long) 3);
        List<Order> listOrderShipped = orderService.findAllByCustomerEmailAndStatus(request.getRemoteUser(), (long) 4);
        List<Order> listFinishedOrder = orderService.findAllByCustomerEmailAndStatus(request.getRemoteUser(), (long) 5);
        List<Order> listOrderIsCanceled = orderService.findAllByCustomerEmailAndStatus(request.getRemoteUser(), (long) 0);
//        find list order by is paid
        List<Order> listWaitForPay = orderService.findAllByCustomerEmailAndIsPaidFalse(request.getRemoteUser());

        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("all", all);
        model.addAttribute("listProcessed", listProcessed);
        model.addAttribute("listOrderReceived", listOrderReceived);
        model.addAttribute("listShipping", listShipping);
        model.addAttribute("listOrderShipped", listOrderShipped);
        model.addAttribute("listFinishedOrder", listFinishedOrder);
        model.addAttribute("listOrderIsCanceled", listOrderIsCanceled);
        model.addAttribute("listWaitForPay", listWaitForPay);
//        return "order/list";
        model.addAttribute("orderPage", "active");
        return "customer/profile";
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
