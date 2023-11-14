package com.cachenote.server.payload.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class SignupResponse implements ValidResponse{
    private String token;


    @JsonProperty("refresh_token")
    private String refreshToken;
}
