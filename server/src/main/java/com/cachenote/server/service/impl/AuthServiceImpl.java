//package com.cachenote.server.service.impl;
//
//
//import com.cachenote.server.entity.UserDoc;
//import com.cachenote.server.payload.Request.LoginRequest;
//import com.cachenote.server.payload.Request.SignupRequest;
//import com.cachenote.server.payload.TokenDto;
//import com.cachenote.server.repository.UserRepository;
//import com.cachenote.server.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//
//@Service
//public class AuthServiceImpl implements AuthService {
//
//
//    private PasswordEncoder passwordEncoder;
//
//
//    private UserRepository userRepository;
//
//
//    @Override
//    public UserDoc signup(SignupRequest signupRequest) throws RuntimeException {
//        String username = signupRequest.getUsername();
//        UserDoc user = userRepository.findByUsername(username);
//        if (user != null) {
//            throw new RuntimeException("UserDoc exist.");
//        }
//
//        UserDoc newUser = new UserDoc();
//        newUser.setUsername(signupRequest.getUsername());
//        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
//
//        return userRepository.save(newUser);
//
//
//    }
//
//    @Override
//    public String login(LoginRequest loginRequest) {
//        String username = loginRequest.getUsername();
//        UserDoc user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new RuntimeException("UserDoc not found");
//        }
//
//        String inputPwd = loginRequest.getPassword();
//        String dbPwd = user.getPassword();
//
//        if (!passwordEncoder.matches(inputPwd, dbPwd)) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        // If the password is valid, generate and return a token or handle the successful login in your preferred way
//        // For example, you can return a JWT token or a simple success message
//        return "Login successful";
//    }
//}
