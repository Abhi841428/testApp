package com.test.Controller;

import com.test.payload.EmailSendDto;
import com.test.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {
@Autowired
  private EmailService emailService;
    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailSendDto details)
    {
        String status = emailService.sendSimpleMail(details);

        return status;
    }

    @PostMapping("/send-email")
    public String sendModifiedEmail(@RequestBody EmailSendDto details) {
        emailService.sendEmail(details.getRecipient(), details.getSubject(), details.getMsgBody());
        return "Email sent successfully!";
    }
}
