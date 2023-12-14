package com.example.springbootmicroservice3.auth;



import com.example.springbootmicroservice3.repository.UserRepository;
import com.example.springbootmicroservice3.utils.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AuthenticationController {
    private final UserRepository repository;
    private final AuthenticationService service;
    @Autowired
    private StorageService storage;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(

            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("adresse") String adresse,
            @RequestParam("image") MultipartFile image
    )
    {   RegisterRequest r=new RegisterRequest();

        r.setUsername(username);
        r.setEmail(email);
        r.setPassword(password);
        r.setPhone(phone);
        r.setAdresse(adresse);


        return ResponseEntity.ok(service.register(r.getUsername(),r.getEmail(),r.getPassword(),r.getPhone(),r.getAdresse(),image));
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file=storage.loadFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+file.getFilename()+"\"").body(file);

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));

    }
}
