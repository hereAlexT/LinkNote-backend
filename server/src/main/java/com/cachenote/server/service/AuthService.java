package com.cachenote.server.service;


import com.cachenote.server.payload.Reponse.LoginResponse;
import com.cachenote.server.payload.Reponse.SignupResponse;
import com.cachenote.server.payload.Request.LoginRequest;
import com.cachenote.server.payload.Request.SignupRequest;
import com.cachenote.server.payload.entity.UserDoc;

public interface AuthService {

    SignupResponse signup(SignupRequest signupRequest);

    LoginResponse login(LoginRequest loginRequest);


}
