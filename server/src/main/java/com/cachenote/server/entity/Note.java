package com.cachenote.server.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("note")
public class Note {

    @Id
    private String id;

    private String body;

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
