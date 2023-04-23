package edu.home.controller.rest.controller;

import java.util.List;

import edu.home.entity.Customer;
import edu.home.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import edu.home.common.entity.CouponInfo;
import edu.home.entity.Coupon;
import edu.home.service.CouponService;
import edu.home.service.UserAccountService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/coupon")
public class CouponsRestController {
	@Autowired
	private CouponService couponService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "findAllIsActive")
	public ResponseEntity<?> findAllIsActive() {
		try {
			return ResponseEntity.ok(couponService.findAllIsActive());
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(value = "getAll")
	public List<Coupon> getAllCoupon() {
		return couponService.findAll();
	}

	@PostMapping(value = "create")
	public ResponseEntity<?> createCoupon(@RequestBody CouponInfo couponInfo) {
		try {
			Coupon coupon = new Coupon();

			coupon.setName(couponInfo.getName());
			coupon.setAmountMoneyCoupon(couponInfo.getAmountMoneyCoupon());
			coupon.setPercentCoupon(couponInfo.getPercentCoupon());
			coupon.setCode(couponInfo.getCode());
			coupon.setCreateDate(couponInfo.getCreateDate());
			coupon.setDescription(couponInfo.getDescription());
			coupon.setEndDate(couponInfo.getEndDate());
			coupon.setIsDisplay(couponInfo.getIsDisplay());
			coupon.setIsFixed(couponInfo.getIsFixed());
			coupon.setStartDate(couponInfo.getStartDate());
			coupon.setStatus(couponInfo.getStatus());
			coupon.setUserLimit(couponInfo.getUserLimit());			
			coupon.setUser(userAccountService.findByUsernameOrEmail(couponInfo.getCreateBy()));

			return ResponseEntity.ok(couponService.create(coupon));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
	}
	@DeleteMapping(value = "delete/{id}")
	public void deleteCoupon(@PathVariable("id") Long id) {
		couponService.delete(id);
	}
	@PutMapping(value = "update")
	public ResponseEntity<?> updateCategory(@RequestBody CouponInfo couponInfo) {
		try {
			Coupon coupon = couponService.findById(couponInfo.getId());
//			Coupon coupon = new Coupon();
			coupon.setId(couponInfo.getId());
			
			coupon.setName(couponInfo.getName());
			coupon.setAmountMoneyCoupon(couponInfo.getAmountMoneyCoupon());
			coupon.setPercentCoupon(couponInfo.getPercentCoupon());
			coupon.setCode(couponInfo.getCode());
			coupon.setCreateDate(couponInfo.getCreateDate());
			coupon.setDescription(couponInfo.getDescription());
			coupon.setEndDate(couponInfo.getEndDate());
			coupon.setIsDisplay(couponInfo.getIsDisplay());
			coupon.setIsFixed(couponInfo.getIsFixed());
			coupon.setStartDate(couponInfo.getStartDate());
			coupon.setStatus(couponInfo.getStatus());
			coupon.setUserLimit(couponInfo.getUserLimit());			
			coupon.setUser(userAccountService.findByUsernameOrEmail(couponInfo.getCreateBy()));
	
			return ResponseEntity.ok(couponService.update(coupon));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
	}
}
