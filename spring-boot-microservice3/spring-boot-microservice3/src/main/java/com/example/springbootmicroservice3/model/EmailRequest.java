package com.example.springbootmicroservice3.model;

import lombok.Data;

@Data
public class EmailRequest {
    private String toEmail;
    private String subject;
    private String body;

    // Getters and setters
}
