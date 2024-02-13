package com.test.service;

import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class EmailService {

//    @Autowired
//    private JavaMailSender emailSender;
//
//    public void sendSimpleMessage(String to, String subject, String text) {
//        try {
//            MimeMessage message = emailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(text, true);
//            emailSender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            // Handle exception
//        }
//    }

        @Value("${twilio.accountSid}")
        private String accountSid;

        @Value("${twilio.authToken}")
        private String authToken;

        @Value("${twilio.phoneNumber}")
        private String twilioPhoneNumber;

        public void sendSms(String to, String messageBody) {
            Twilio.init(accountSid, authToken);
            Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(twilioPhoneNumber), messageBody).create();
            System.out.println("Message SID: " + message.getSid());
        }
    }


