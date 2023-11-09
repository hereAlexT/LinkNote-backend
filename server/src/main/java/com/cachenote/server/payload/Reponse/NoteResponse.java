package com.cachenote.server.payload.Reponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteResponse {
    private String id;
    private String body;
}
