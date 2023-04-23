package edu.home.controller.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.common.entity.CustomerCouponDto;
import edu.home.entity.CustomerCoupon;
import edu.home.service.CouponService;
import edu.home.service.CustomerCouponService;
import edu.home.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/customerCoupon")
public class CustomerCouponRestController {
	@Autowired
	private CustomerCouponService customerCouponService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CouponService couponService;

	@GetMapping("/getAll")
	public List<CustomerCoupon> findAll(){
		return customerCouponService.findAll();
	}
	@GetMapping("/findByEmail/{email}")
	public List<CustomerCoupon> findByEmail(@PathVariable("email") String email){
		return customerCouponService.findByEmail(email);
	}
	@GetMapping("/findByCouponId/{code}")
	public List<CustomerCoupon> findByCouponId(@PathVariable("code") Long code){
		return customerCouponService.findByCouponId(code);
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		customerCouponService.deleteById(id);
	}
	@PostMapping("/add")
	public CustomerCoupon add(@RequestBody CustomerCouponDto customerCouponDto) {
		CustomerCoupon cus = new CustomerCoupon();
		cus.setCustomer(customerService.findByEmail(customerCouponDto.getCustomerEmail()));
		cus.setCoupon(couponService.findById(customerCouponDto.getCouponId()));
		cus.setCreateDate(customerCouponDto.getCreateDate());
		cus.setStatus(customerCouponDto.getStatus());
		
		return customerCouponService.save(cus);
	}

	@PostMapping(value = "create")
	public ResponseEntity<?> createCustomerCoupon(@RequestBody CustomerCoupon customerCoupon) {
		try {
			if (customerCouponService.getByCustomerEmailAndCouponId(customerCoupon.getCustomer().getEmail(), customerCoupon.getCoupon().getId()) == null)
				return ResponseEntity.ok(customerCouponService.save(customerCoupon));
			else {
				customerCouponService.updateCustomerCouponStatusByCustomerEmailAndCouponId(customerCoupon.getStatus() + 1, customerCoupon.getCustomer().getEmail(), customerCoupon.getCoupon().getId());
				return ResponseEntity.ok(customerCoupon);
			}
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{emailCustomer}/{idCoupon}")
	public void deleteByEmailCustomerAndIdCoupon(@PathVariable("emailCustomer") String emailCustomer, @PathVariable("idCoupon") Long idCoupon) {
		customerCouponService.customerCouponService(emailCustomer,idCoupon);
	}
}
