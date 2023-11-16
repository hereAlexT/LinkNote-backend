package com.cachenote.server.payload.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotBlank(message = "Email Required")
    @Email(message = "Invalid Email")
    @Size(min = 3, max = 100, message = "Email address too long or too short.")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 32, message = "Password must be between 8 - 32 characters long.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,32}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    @JsonProperty("pwd")
    private String password;


    @Size(min = 2, max = 32, message = "Display name must be between 2 - 32 characters long.")
    @NotBlank
    @JsonProperty("dis_name")
    private String displayName;
}
