package com.cachenote.server.payload.reponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadCredentialsResponse implements ValidResponse {
    private String message;
}
