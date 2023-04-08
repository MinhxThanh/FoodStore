package edu.home.controller.rest.controller;

import java.util.List;

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

import edu.home.common.entity.CustomerInfo;
import edu.home.entity.Customer;
import edu.home.service.CustomerService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/rest/customer")
public class CustomerRestController {
	@Autowired
	CustomerService customerService;

	@GetMapping("findCustomerIsPresent")
	public ResponseEntity<?> findCustomerIsPresent(HttpServletRequest request) {
		try {
			if (request.getRemoteUser() != null)
				return ResponseEntity.ok(customerService.findByEmailKey(request.getRemoteUser()));
			else
				return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(value = "getAll")
	public List<Customer> getAllcustomer() {
		System.out.println("\n dang chay getall customer \n");
		return customerService.findAll();
	}

	@PostMapping(value = "create")
	public ResponseEntity<?> createcustomer(@RequestBody CustomerInfo customerInfo) {
		try {

			Customer customer = new Customer();

////			customer.setId(customerInfo.getId());
//			customer.setContent(customerInfo.getContent());
//			customer.setCreateDate(customerInfo.getCreateDate());
//			customer.setIsDisplay(customerInfo.getIsDisplay());
//			customer.setStatus(customerInfo.getStatus());
//			customer.setTitle(customerInfo.getTitle());
//			customer.setViewCount(customerInfo.getViewCount());
//			customer.setUser(userAccountService.findByUsernameOrEmail(customerInfo.getCreateBy()));
			
			
			customer.setEmail(customerInfo.getEmail());
			customer.setCreateDate(customerInfo.getCreateDate());
			customer.setAvatar(customerInfo.getAvatar());
			customer.setBirthday(customerInfo.getBirthday());
			customer.setFirstName(customerInfo.getFirstName());
			customer.setFullname(customerInfo.getFullname());
			customer.setLastName(customerInfo.getLastName());
			customer.setGender(customerInfo.getGender());
			
			customer.setIsDisplay(customerInfo.getIsDisplay());
			
//			customer.setIsDisplay(false);
			
			customer.setPassword(customerInfo.getPassword());
			customer.setRememberToken(customerInfo.getRememberToken());
			customer.setStatus(customerInfo.getStatus());
         
         

			return ResponseEntity.ok(customerService.create(customer));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
	}

//	@DeleteMapping(value = "delete/{id}")
//	public void deletecustomer(@PathVariable("id") Long id) {
//		System.out.println("\n dang chay delete \n");
//		customerService.delete(id);
//	}

	@PutMapping(value = "update")
	public ResponseEntity<?> updateCategory(@RequestBody CustomerInfo customerInfo) {
		try {
//			customer oldcustomer = customerService.findById(customerInfo.getId());
			Customer customer = new Customer();
//
//			customer.setId(customerInfo.getId());
//			customer.setContent(customerInfo.getContent());
//			
//			customer.setCreateDate(oldcustomer.getCreateDate());//?
//			
//			customer.setIsDisplay(customerInfo.getIsDisplay());
//			customer.setStatus(customerInfo.getStatus());
//			customer.setTitle(customerInfo.getTitle());
//			customer.setViewCount(customerInfo.getViewCount());
//			
//			customer.setUser(userAccountService.findByUsernameOrEmail(customerInfo.getCreateBy()));

			return ResponseEntity.ok(customerService.create(customer));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
	}
}
