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

    @GetMapping("/home")
    public String home(){
        return "U-Friend Home";
    }

    @Autowired
    MailService mailService;

    @EventListener(ApplicationReadyEvent.class)
    public void test() {
        mailService.send("alejito23001@gmail.com", "TOKEN", EmailTemplate.ACCOUNT_DELETED);
    }

}
