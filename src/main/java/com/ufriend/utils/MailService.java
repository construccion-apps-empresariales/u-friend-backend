package com.ufriend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.ufriend.enums.EmailTemplate;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Data
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String from;

    @Value("${server.host}")
    private String host;

    public Boolean sendConfirmationEmail(String to, String token) {
        return send(EmailTemplate.CONFIRMATION, to, token);
    }
    
    public Boolean sendRestorePasswordEmail(String to, String token) {
        return send(EmailTemplate.RESTORE_PASSWORD, to, token);
    }

    public Boolean sendAccountDeletedEmail(String to) {
        String token = "";
        return send(EmailTemplate.ACCOUNT_DELETED, to, token);
    }

    private Boolean send(EmailTemplate template, String to, String token) {
        MimeMessagePreparator message = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom(this.from);
            helper.setTo(to);
            helper.setSubject(template.getSubject());
            String content = template.getContent()
                .replace("TOKEN", token)
                .replace("HOST", host);
            log.info(host);
            helper.setText(content, true);
        };
        
        try {
            this.mailSender.send(message);
            log.info(String.format("Email sended to %s with template %s", to, template.getSubject()));
            return true;
        }
        catch (Exception e) {
            log.error(String.format("Error sending email to %s.\nError:%s", to, e.toString()), e);
            return false;
        }        
    }

}
