package edu.home.controller.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.home.entity.Paymentmethod;
import edu.home.service.PaymentmethodService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "rest/paymentmethod")
public class PaymentmethodRestControll {
	@Autowired
	private PaymentmethodService paymentmethodService;
	
	@GetMapping("/findAll")
	public List<Paymentmethod> findAll(){
		return paymentmethodService.findAll();
	}
}
