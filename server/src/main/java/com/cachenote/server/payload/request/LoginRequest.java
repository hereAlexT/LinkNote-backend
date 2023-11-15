package com.cachenote.server.payload.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email Required")
    @Email(message = "Invalid Email")
    @Size(min = 3, max = 100, message = "Email address too long or too short.")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 32, message = "Password must be between 8 - 32 characters long.")
    @JsonProperty("pwd")
    private String password;
}
