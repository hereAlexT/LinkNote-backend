package com.cachenote.server.security.service;


import com.cachenote.server.common.exception.BadUsernamePasswordException;
import com.cachenote.server.payload.reponse.LoginResponse;
import com.cachenote.server.payload.reponse.SignupResponse;
import com.cachenote.server.payload.reponse.ValidResponse;
import com.cachenote.server.payload.request.LoginRequest;
import com.cachenote.server.payload.request.SignupRequest;
import com.cachenote.server.payload.entity.Role;
import com.cachenote.server.payload.entity.User;
import com.cachenote.server.repository.RoleRepository;
import com.cachenote.server.repository.UserRepository;
import com.cachenote.server.payload.entity.UserRole;
import com.cachenote.server.security.entity.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;




    @Override
    public SignupResponse signup(SignupRequest signupRequest) {
        // todo: check if the username exist or nor

        User user = new User(
                signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()),
                Collections.singleton(UserRole.ROLE_USER_REGISTERED));
        Set<Role> roles =user.getRoles().stream()
                .map(roleEnum -> roleRepository.findByName(roleEnum.getName())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleEnum.getName())))
                .collect(Collectors.toSet());

        user.setRoles(roles);

        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);
        repository.save(user);

        var jwtToken = jwtService.generateToken(userDetailsImpl);
        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setToken(jwtToken);
        return signupResponse;
    }



    @Override
    public ValidResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
//            return new BadCredentialsResponse("Wrong username or password.");
            throw new BadUsernamePasswordException("Wrong username or password.");
        }

        Optional<User> user = repository.findByEmail(loginRequest.getUsername());
        if (user.isEmpty()) {
            throw new BadUsernamePasswordException("User name not found.");
        }

        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user.get());
        // generate token
        // todo: do we needs to generate token when user login?
        var jwtToken = jwtService.generateToken(userDetailsImpl);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);

        return loginResponse;

    }



}
