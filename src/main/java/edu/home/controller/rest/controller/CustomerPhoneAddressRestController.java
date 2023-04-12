package edu.home.controller.rest.controller;

import edu.home.service.CustomerPhoneAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
}
