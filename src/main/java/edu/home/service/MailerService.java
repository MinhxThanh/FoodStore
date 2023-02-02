package edu.home.service;

import edu.home.common.entity.MailInfoCustomer;
import edu.home.common.entity.MailInfoWelcome;

import javax.mail.MessagingException;

public interface MailerService {
    void sendMailForgotPassword(MailInfoCustomer mail) throws MessagingException;
    void sendMailWelcome(MailInfoWelcome mail) throws MessagingException;

}
