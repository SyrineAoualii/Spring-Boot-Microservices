package com.example.springbootmicroservice3.auth;




import com.example.springbootmicroservice3.model.Role;
import com.example.springbootmicroservice3.repository.UserRepository;
import com.example.springbootmicroservice3.security.jwt.JwtService;
import com.example.springbootmicroservice3.utils.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.example.springbootmicroservice3.model.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    @Autowired
    private StorageService storageService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register( String username,String email, String password,String phone,String adresse,MultipartFile image) {
        var user = User.builder()

                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .phone(phone)
                .adresse(adresse)
                .role(Role.USER)
                .createTime(LocalDateTime.now())
                .build();
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        user.setImage(filename);
        storageService.store(image);

        repository.save(user); // Move this line here

        // Generate JWT token and return the authentication response
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userRole(Role.USER)
                .id(user.getId())
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userRole(user.getRole())
                .id(user.getId())
                .username(user.getUsername())
                .image((user.getImage()))
                .email(user.getEmail())
                .build();


    }


}
