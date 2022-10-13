package com.ufriend.enums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public enum EmailTemplate {
    
    CONFIRMATION(
        "Confirmación de correo",
        getHtmlFile("confirmation") 
    ),
    ACCOUNT_DELETED(
        "Cuenta eliminada",
        getHtmlFile("account_deleted")
    ),
    RESTORE_PASSWORD(
        "Restauración de contraseña",
        getHtmlFile("restore_password")
    );

    private String content;
    private String subject;

    private EmailTemplate (String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    private static String getHtmlFile(String fileName) {
        fileName = String.format("src/main/resources/templates/email/%s.html", fileName);
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        String content = contentBuilder.toString();
        return content;
    }

    public String getContent() {
        return this.content;
    }

    public String getSubject() {
        return this.subject;
    }

}
