package apimanga.example.manga.controllers;

import apimanga.example.manga.dtos.AuthData;
import apimanga.example.manga.models.User;
import apimanga.example.manga.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity doLogin(@RequestBody @Valid AuthData data) {
        var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = manager.authenticate(token);

        var jwt = tokenService.getToken((User) authentication.getPrincipal());

        return ResponseEntity.ok().body((jwt));
    }
}