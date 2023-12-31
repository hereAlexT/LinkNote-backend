package com.linknote.server.payload.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
