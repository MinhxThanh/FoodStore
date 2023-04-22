package edu.home.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import edu.home.common.entity.OrderDetail;

public interface PaymentService {
    String authorizePayment(OrderDetail orderDetail) throws PayPalRESTException;
    Payment getPaymentDetails(String paymentId) throws PayPalRESTException;
    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
