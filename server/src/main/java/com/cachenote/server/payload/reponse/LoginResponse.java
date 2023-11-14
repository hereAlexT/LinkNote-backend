package com.cachenote.server.payload.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements ValidResponse{
    private String token;
    @JsonProperty("expires_in")
    private String expiresIn; //todo: add refreshtime
}
