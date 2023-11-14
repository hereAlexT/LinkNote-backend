package com.cachenote.server.payload.reponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteResponse {
    private Long id;
    private String body;
}
