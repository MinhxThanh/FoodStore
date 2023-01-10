package edu.home.repository;

import edu.home.entity.Paymentmethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentmethodRepository extends JpaRepository<Paymentmethod, Long> {
}