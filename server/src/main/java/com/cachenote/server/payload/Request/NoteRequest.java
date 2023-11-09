package com.cachenote.server.payload.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteRequest {
    private String id;
    private String body;

}
