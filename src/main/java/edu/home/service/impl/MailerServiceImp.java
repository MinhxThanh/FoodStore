package edu.home.service.impl;

import edu.home.common.entity.MailInfoCustomer;
import edu.home.common.entity.MailInfoWelcome;
import edu.home.common.mailHelper.FormMailForgotPassword;
import edu.home.common.mailHelper.FormMailWelcome;
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
    @Autowired
    private FormMailWelcome formMailWelcome;

    @Override
    public void sendMailForgotPassword(MailInfoCustomer mail) throws MessagingException {
        String html = formMailForgotPassword.content(mail.getUsername(), mail.getTo());
        setSender(mail.getFrom(), mail.getTo(), mail.getSubject(), mail.getFrom(), html);
    }

    @Override
    public void sendMailWelcome(MailInfoWelcome mail) throws MessagingException {
        String html = formMailWelcome.content(mail.getFullname(), mail.getTo(), mail.getPassword(), mail.getImage());
        setSender(mail.getFrom(), mail.getTo(), mail.getSubject(), mail.getFrom(), html);
    }

    private void setSender(String form, String to, String subject, String reply, String html) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        helper.setFrom(form);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(html, true);
        helper.setReplyTo(reply);

        // Gửi message đến SMTP server
        sender.send(message);
        System.out.println("send!!!!!!!");
    }
}
