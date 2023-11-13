package com.cachenote.server.service.impl;


import com.cachenote.server.payload.Reponse.LoginResponse;
import com.cachenote.server.payload.Reponse.SignupResponse;
import com.cachenote.server.payload.Request.LoginRequest;
import com.cachenote.server.payload.Request.SignupRequest;
import com.cachenote.server.payload.entity.User;
import com.cachenote.server.repository.UserRepository;
import com.cachenote.server.security.service.JwtService;
import com.cachenote.server.security.UserRole;
import com.cachenote.server.security.entity.UserDetailsImpl;
import com.cachenote.server.service.AuthService;
import lombok.RequiredArgsConstructor;
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

        User user = new User(
                signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()),
                UserRole.ROLE_USER_REGISTERED);
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);
        repository.save(user);

        var jwtToken = jwtService.generateToken(userDetailsImpl);
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
        User user = repository.findByUsername(loginRequest.getUsername());
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);
        // generate token
        // todo: do we needs to generate token when user login?
        var jwtToken = jwtService.generateToken(userDetailsImpl);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);

        return loginResponse;

    }



}
