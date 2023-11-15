package com.cachenote.server.payload.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements ValidResponse {
    @JsonProperty("token")
    private TokenResponse tokenResponse;
    @JsonProperty("user")
    private UserResponse userResponse;

//    @JsonProperty("exp_dt")
//    private LocalDateTime expiredDateTime;

}
