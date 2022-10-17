package com.ufriend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufriend.enums.EmailTemplate;
import com.ufriend.utils.MailService;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "U-Friend Backend, we are working onn it!_";
    }

    @Autowired
    MailService mailService;

    @EventListener(ApplicationReadyEvent.class)
    public void sendEmailTest() {
        mailService.send("alejito23001@gmail.com", "TOKEN", EmailTemplate.RESTORE_PASSWORD);
    }

}
