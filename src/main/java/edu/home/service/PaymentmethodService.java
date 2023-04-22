package edu.home.service;

import java.util.List;

import edu.home.entity.Paymentmethod;

public interface PaymentmethodService {
	List<Paymentmethod> findAll();

    Paymentmethod findById(long id);
}
