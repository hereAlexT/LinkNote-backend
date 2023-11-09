package com.cachenote.server.payload.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("note")
public class NoteDoc {

    @Id
    private String id;

    private String body;

    @Override
    public String toString() {
        return "NoteDoc{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
