package com.cachenote.server.payload.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNoteRequest {
    @NotBlank(message = "Cannot submit empty note")
    @Size(max = 1000, message="Note length at most 1000 characters' long.")
    private String body;
}
