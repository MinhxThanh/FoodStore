package edu.home.controller.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.entity.CustomerPhoneAddress;
import edu.home.service.CustomerPhoneAddressService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "rest/customerPhoneAddress")
public class CustomerPhoneAddressRestController {
    @Autowired
    private CustomerPhoneAddressService addressService;

    @GetMapping(value = "findByCustomerEmail")
    public ResponseEntity<?> findByCustomerEmail(HttpServletRequest request){
        try {
            if (request.getRemoteUser() != null)
                return ResponseEntity.ok(addressService.findByCustomerEmail(request.getRemoteUser()));
            else
                return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "findAllByCustomerEmail")
    public ResponseEntity<?> findAllByCustomerEmail(HttpServletRequest request){
        try {
            return ResponseEntity.ok(addressService.findAllByCustomerEmail(request.getRemoteUser()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//  @PostMapping(value = "create")
//  public ResponseEntity<?> create(@RequestBody CustomerPhoneAddress form, HttpServletRequest request){
//  	try {
//  		Customer customer = customerService.findByEmailKey(request.getRemoteUser());
//  		CustomerPhoneAddress item = new CustomerPhoneAddress();
//  		item.setCustomer(customer);
//  		item.setPhone(form.getPhone());
//  		item.setAddress(form.getAddress());
//  		item.setCityProvince(form.getCityProvince());
//  		item.setDefault(false);
//  		item.setUsername(form.getUsername());
//  		return ResponseEntity.ok(addressService.create(item));
//  	} catch (Exception e) {
//          return ResponseEntity.noContent().build();
//	    }
//	}
//  @PostMapping(value = "create")
//  public ResponseEntity<CustomerPhoneAddress> createCPA(@RequestBody CustomerPhoneAddress form){
//  		CustomerPhoneAddress item = new CustomerPhoneAddress();
//  		item.setAddress(form.getAddress());
//  		item.setUsername(form.getUsername());
//  		item.setCityProvince(form.getCityProvince());
//  		item.setDefault(false);
//  		item.setPhone(form.getPhone());
//  		addressService.create(item);
//  		
//  		return ResponseEntity.ok(form);
//	}
//  @PostMapping(value = "create")
//  public CustomerPhoneAddress createContact(@RequestBody CustomerPhoneAddress customerPhoneAddress) {
//  	CustomerPhoneAddress phoneAddress = addressService.create(customerPhoneAddress);
//  	return phoneAddress;
//  }
  @PutMapping(value = "update/{id}")
  public CustomerPhoneAddress update(@PathVariable("id") Long id,@RequestBody CustomerPhoneAddress customerPhoneAddress) {
  	CustomerPhoneAddress cu = addressService.update(customerPhoneAddress);
  	return cu;
  	}

//  @PutMapping(value = "setdf")
//  public CustomerPhoneAddress updateDefault(@RequestBody CustomerPhoneAddress customerPhoneAddress,HttpServletRequest request) {
//  	List<CustomerPhoneAddress> list = addressService.findAllByCustomerEmail(request.getRemoteUser());
//  	customerPhoneAddress.setDefault(true);
//  	CustomerPhoneAddress address = addressService.update(customerPhoneAddress);
//  	for (CustomerPhoneAddress obj : list) {
//			obj.setDefault(false);
//			addressService.create(address);
//		}
//  	return address;
//  }
  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable("id") Long id) {
  	addressService.deleteById(id);
  }
  

}
