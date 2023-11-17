package com.linknote.server.security.service;

import com.linknote.server.common.exception.AccountExistException;
import com.linknote.server.common.exception.BadUsernamePasswordException;
import com.linknote.server.payload.entity.Role;
import com.linknote.server.payload.entity.User;
import com.linknote.server.payload.entity.UserRole;
import com.linknote.server.payload.Dto.LoginResponse;
import com.linknote.server.payload.Dto.TokenResponse;
import com.linknote.server.payload.Dto.UserResponse;
import com.linknote.server.payload.Dto.ValidResponse;
import com.linknote.server.payload.Dto.LoginRequest;
import com.linknote.server.payload.Dto.SignupRequest;
import com.linknote.server.repository.RoleRepository;
import com.linknote.server.repository.UserRepository;
import com.linknote.server.security.entity.UserDetailsImpl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
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
    public Void signup(SignupRequest signupRequest) {


        User user = new User(
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()),
                Collections.singleton(UserRole.ROLE_USER_REGISTERED));
        Set<Role> roles = user.getRoles().stream()
                .map(roleEnum -> roleRepository.findByName(roleEnum.getName())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleEnum.getName())))
                .collect(Collectors.toSet());

        user.setRoles(roles);
        user.setDisplayName(signupRequest.getDisplayName());

        try {
            repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new AccountExistException(signupRequest.getEmail());
        }

        return null;
    }


    @Override
    public ValidResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadUsernamePasswordException("Wrong username or password.");
        }
        Optional<User> user = repository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()) {
            throw new BadUsernamePasswordException("User name not found.");
        }
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user.get());
        TokenResponse tokenResponse = jwtService.generateTokenResponse(userDetailsImpl);
        UserResponse userResponse = new UserResponse(user.get().getId(), user.get().getDisplayName());

        return new LoginResponse(tokenResponse, userResponse);
    }
}
