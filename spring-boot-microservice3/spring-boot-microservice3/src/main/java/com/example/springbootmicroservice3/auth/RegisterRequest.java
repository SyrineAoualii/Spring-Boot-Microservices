package com.example.springbootmicroservice3.auth;

import com.example.springbootmicroservice3.model.Role;
import com.example.springbootmicroservice3.model.Statut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String email;

    private String username;

    private String password;
    private String phone;
    private String adresse;
    private String image;

    public Role role;






}
