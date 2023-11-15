package com.cachenote.server.security.service;


import com.cachenote.server.payload.Dto.ValidResponse;
import com.cachenote.server.payload.Dto.LoginRequest;
import com.cachenote.server.payload.Dto.SignupRequest;

public interface AuthService {

    Void signup(SignupRequest signupRequest);

    ValidResponse login(LoginRequest loginRequest);


}
