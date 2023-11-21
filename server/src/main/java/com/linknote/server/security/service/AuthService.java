package com.linknote.server.security.service;


import com.linknote.server.payload.Dto.ValidResponse;
import com.linknote.server.payload.Dto.LoginRequest;
import com.linknote.server.payload.Dto.SignupRequest;

public interface AuthService {

    Void signup(SignupRequest signupRequest);

    ValidResponse login(LoginRequest loginRequest);


}
