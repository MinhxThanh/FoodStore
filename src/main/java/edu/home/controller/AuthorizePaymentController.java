package edu.home.controller;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import edu.home.service.OrderService;
import edu.home.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizePaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "review", method = RequestMethod.GET)
    public String reviewDetail(Model model,
                               @RequestParam("PayerID") String payerID,
                                       @RequestParam("paymentId") String paymentId) throws PayPalRESTException {
        Payment payment = paymentService.getPaymentDetails(paymentId);

        PayerInfo payerInfo = payment.getPayer().getPayerInfo();
        Transaction transaction = payment.getTransactions().get(0);
        ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();

        model.addAttribute("payerInfo", payerInfo);
        model.addAttribute("transaction", transaction);
        model.addAttribute("shippingAddress", shippingAddress);
//        model.addAttribute("payerID", payerID);
//        model.addAttribute("paymentId", paymentId);

        System.out.println("payer ID: " + payerID);
        System.out.println("payment Id: " + paymentId);

        return "checkout/review";
//        return "redirect:/review?paymentId=" + paymentId + "&PayerID=" + payerID;
    }

    @RequestMapping(value = "execute-payment", method = RequestMethod.POST)
    public String executePayment(Model model, @RequestParam("paymentId") String paymentId,
                                 @RequestParam("payerID") String payerID) throws PayPalRESTException {
        Payment payment = paymentService.executePayment(paymentId, payerID);

        PayerInfo payerInfo = payment.getPayer().getPayerInfo();
        Transaction transaction = payment.getTransactions().get(0);

        Long orderId = Long.valueOf(transaction.getDescription().substring(11,16));
        System.out.println("Order Id: " + orderId);
        orderService.updateIsPaidByOrderId(orderId);

        model.addAttribute("payerInfo", payerInfo);
        model.addAttribute("transaction", transaction);

        return "checkout/receipt";
    }

}
