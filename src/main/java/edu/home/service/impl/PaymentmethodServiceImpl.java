package edu.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.home.entity.Paymentmethod;
import edu.home.repository.PaymentmethodRepository;
import edu.home.service.PaymentmethodService;

@Service
public class PaymentmethodServiceImpl implements PaymentmethodService{
	@Autowired
	private PaymentmethodRepository dao;
	@Override
	public List<Paymentmethod> findAll() {
		return dao.findAll();
	}

	@Override
	public Paymentmethod findById(long id) {
		return dao.findById(id).get();
	}

}
