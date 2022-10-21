package com.ufriend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufriend.utils.MailService;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "U-Friend Backend, we are working onn it!_";
    }

    @Autowired
    MailService mailService;

}
