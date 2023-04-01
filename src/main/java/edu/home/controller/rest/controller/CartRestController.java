package edu.home.controller.rest.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import edu.home.service.CartService;

@RestController
@RequestMapping(value = "rest/cart")
public class CartRestController {
    @Autowired
    private CartService cartService;

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll(HttpServletRequest request){
        try {
            if (request.getRemoteUser() != null)
                return ResponseEntity.ok(cartService.getAllByCustomerEmail(request.getRemoteUser()));
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "create")
    public ResponseEntity<?> create(@RequestBody JsonNode cartJson){
        try {
            return ResponseEntity.ok(cartService.create(cartJson));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "synchronize")
    public ResponseEntity<?> synchronize(@RequestBody JsonNode orderJsonData) throws MessagingException {
        try {
            return ResponseEntity.ok(cartService.create(orderJsonData));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "delete/{id}")
    public void delete(@PathVariable("id") Long foodId, HttpServletRequest request){
        try {
            cartService.deleteCartByCustomerEmailAndFoodId(request.getRemoteUser(), foodId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
