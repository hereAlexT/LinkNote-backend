package com.linknote.server.security.controller;


import com.linknote.server.payload.Dto.ValidResponse;
import com.linknote.server.payload.Dto.LoginRequest;
import com.linknote.server.payload.Dto.SignupRequest;
import com.linknote.server.security.service.AuthService;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<ValidResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid SignupRequest signupRequest) {
        return ResponseEntity.ok(authService.signup(signupRequest));


    }
}
