package com.test.Controller;


import com.test.service.TextMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TextMessageController {


    @Autowired
    private TextMessageService textMessageService;

//    @PostMapping("/sendEmail")
//    public void sendEmail(@RequestBody EmailDTO emailDTO) {
//        emailService.sendSimpleMessage(emailDTO.getTo(), emailDTO.getSubject(), emailDTO.getText());
//    }

    @PostMapping("/text")
    public void sendEmail() {
        textMessageService.sendSms("+918709652386","Just testing");

    }
}
