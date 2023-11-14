package com.cachenote.server.security.service;


import com.cachenote.server.payload.Reponse.ValidResponse;
import com.cachenote.server.payload.Request.LoginRequest;
import com.cachenote.server.payload.Request.SignupRequest;

public interface AuthService {

    ValidResponse signup(SignupRequest signupRequest);

    ValidResponse login(LoginRequest loginRequest);


}
