package edu.home.service;

import edu.home.common.entity.MailInfoCustomer;

import javax.mail.MessagingException;

public interface MailerService {
    void sendMailForgotPassword(MailInfoCustomer mail) throws MessagingException;
}
