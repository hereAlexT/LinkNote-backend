package com.cachenote.server.service.impl;


import com.cachenote.server.payload.Reponse.LoginResponse;
import com.cachenote.server.payload.Reponse.SignupResponse;
import com.cachenote.server.payload.Request.LoginRequest;
import com.cachenote.server.payload.Request.SignupRequest;
import com.cachenote.server.payload.entity.UserDoc;
import com.cachenote.server.repository.UserRepository;
import com.cachenote.server.security.JwtService;
import com.cachenote.server.security.Role;
import com.cachenote.server.security.MyUserDetails;
import com.cachenote.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    @Override
    public SignupResponse signup(SignupRequest signupRequest) {
        // todo: check if the username exist or nor

        UserDoc userDoc = new UserDoc(
                signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()),
                Role.USER_NORMAL);
        MyUserDetails myUserDetails = new MyUserDetails(userDoc);
        repository.save(userDoc);

        var jwtToken = jwtService.generateToken(myUserDetails);
        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setToken(jwtToken);
        return signupResponse;
    }



    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        UserDoc userDoc = repository.findByUsername(loginRequest.getUsername());
        MyUserDetails myUserDetails = new MyUserDetails(userDoc);
        // generate token
        // todo: do we needs to generate token when user login?
        var jwtToken = jwtService.generateToken(myUserDetails);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);

        return loginResponse;

    }



}
