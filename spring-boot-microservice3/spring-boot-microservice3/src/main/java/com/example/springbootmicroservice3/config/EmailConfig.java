package com.example.springbootmicroservice3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // Serveur SMTP Gmail
        mailSender.setPort(587); // Port SMTP (587 est généralement utilisé pour TLS)
        mailSender.setUsername("ddevnovate@gmail.com"); // Votre adresse e-mail
        mailSender.setPassword("mbif bnvc joks evqt"); // Votre mot de passe

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true"); // Activer le débogage

        return mailSender;
    }
}

