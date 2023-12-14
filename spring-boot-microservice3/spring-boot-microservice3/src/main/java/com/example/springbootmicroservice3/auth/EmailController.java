package com.example.springbootmicroservice3.auth;
import com.example.springbootmicroservice3.model.EmailRequest;
import com.example.springbootmicroservice3.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
@RestController
public class EmailController {
    @Autowired
    private EmailSenderService emailSenderService;
    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        emailSenderService.sendSimpleEmail(emailRequest.getToEmail(), emailRequest.getSubject(), emailRequest.getBody());
        return "Email sent successfully";
    }
}
