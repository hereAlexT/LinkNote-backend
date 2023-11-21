package Dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequest {

    private String email;

    @JsonProperty("pwd")
    private String password;

    @JsonProperty("dis_name")
    private String displayName;

}

