package com.cachenote.server.security.service;


import com.cachenote.server.payload.reponse.ValidResponse;
import com.cachenote.server.payload.request.LoginRequest;
import com.cachenote.server.payload.request.SignupRequest;

public interface AuthService {

    Void signup(SignupRequest signupRequest);

    ValidResponse login(LoginRequest loginRequest);


}
