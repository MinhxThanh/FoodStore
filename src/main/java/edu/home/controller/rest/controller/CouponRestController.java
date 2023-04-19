package edu.home.controller.rest.controller;

import edu.home.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "rest/coupon")
public class CouponRestController {
    @Autowired
    private CouponService couponService;

    @GetMapping(value = "findAllCouponByCustomer")
    public ResponseEntity<?> findAllCouponByCustomer(HttpServletRequest request) {
        try {
            return ResponseEntity.ok(couponService.findAllCouponByCustomerByEmail(request.getRemoteUser()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "findByCode/{code}")
    public ResponseEntity<?> findByCode(@PathVariable("code") String code){
        try {
            return ResponseEntity.ok(couponService.findByCode(code));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(couponService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "findAllByUserEmail/{email}")
    public ResponseEntity<?> findAllByUserEmail(@PathVariable("email") String email) {
        try {
            return ResponseEntity.ok(couponService.findAllByUserEmail(email));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}
