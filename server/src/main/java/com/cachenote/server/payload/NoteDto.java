package com.cachenote.server.payload;


import lombok.Data;

import java.util.UUID;

@Data
public class NoteDto {
    private String id;
    private String body;

}
