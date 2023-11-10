package com.cachenote.server.payload.Reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class SignupResponse {
    private String token;


    @JsonProperty("refresh_token")
    private String refreshToken;
}
