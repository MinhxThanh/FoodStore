package edu.home.controller.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.entity.Customer;
import edu.home.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "rest/customerProfile")
public class CustomerProfileRestController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(value = "findByCustomerEmail")
	public ResponseEntity<?> findByCustomerEmail(HttpServletRequest request){
		try {
            if (request.getRemoteUser() != null)
                return ResponseEntity.ok(customerService.findByCustomerEmail(request.getRemoteUser()));
            else
                return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
	}
	
	@PutMapping(value = "update/{email}")	
	public Customer update(@RequestBody Customer customer,@PathVariable("email")String email) {
		Customer c =customerService.update(customer);
		return c;
	}
}
