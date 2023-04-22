package edu.home.controller;
import edu.home.entity.CustomerCoupon;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.home.service.CouponService;
import edu.home.service.CustomerCouponService;
import edu.home.service.CustomerService;

@Controller
@RequestMapping("customerCoupon")
public class CustomerCouponController {
	
	@Autowired
	CustomerCouponService customerCouponService;
	@Autowired
	CustomerService customerService;
	@Autowired
	CouponService couponService;
	
	@PostMapping("/create")
	public String postCustomerCoupon(Model model, @RequestParam("usernameId") String email,
            @RequestParam("couponId") Long couponId) {
		if(couponId==null || email.isBlank()) {
			return "redirect:/food/shop";
		}
		
		CustomerCoupon cusCp = customerCouponService.findByCustomerEmailAndCouponId(email,couponId);
		if(cusCp != null) {
			customerCouponService.updateCustomerCouponByCustomerEmailAndCouponId(email,couponId);
		}else {
			CustomerCoupon customerCp = new CustomerCoupon();
			customerCp.setCustomer(customerService.findByEmailKey(email));
			customerCp.setCoupon(couponService.findById(couponId));
			customerCp.setCreateDate(new Date());
			customerCp.setStatus(0);
			
			customerCouponService.create(customerCp);
		}
		return "redirect:/food/shop";
	}
}
