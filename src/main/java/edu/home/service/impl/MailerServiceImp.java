package edu.home.service.impl;

import edu.home.common.entity.MailInfoCustomer;
import edu.home.common.mailHelper.FormMailForgotPassword;
import edu.home.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailerServiceImp implements MailerService {
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private FormMailForgotPassword formMailForgotPassword;

    @Override
    public void sendMailForgotPassword(MailInfoCustomer mail) throws MessagingException {
        String html = formMailForgotPassword.content(mail.getUsername(), mail.getTo());
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(html, true);
        helper.setReplyTo(mail.getFrom());

        // Gửi message đến SMTP server
        sender.send(message);
        System.out.println("send!!!!!!!");
    }
}
