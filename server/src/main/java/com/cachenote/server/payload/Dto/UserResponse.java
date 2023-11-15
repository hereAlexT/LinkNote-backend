package com.cachenote.server.payload.Dto;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    @JsonProperty("dis_name")
    private String displayName;

//    @JsonProperty("last_edit_dt")
//    private LocalDatetime lastEditDateTime;
}
