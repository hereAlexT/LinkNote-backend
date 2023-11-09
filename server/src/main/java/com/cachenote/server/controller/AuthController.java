//package com.cachenote.server.controller;
//
//
//import com.cachenote.server.entity.UserDoc;
//import com.cachenote.server.payload.Request.LoginRequest;
//import com.cachenote.server.payload.Request.SignupRequest;
//import com.cachenote.server.service.AuthService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthController {
//    private AuthService authService;
//
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        String response = authService.login(loginRequest);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<UserDoc> login(@RequestBody SignupRequest signupRequest) {
//        UserDoc user = authService.signup(signupRequest);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }
//
//}
