package com.cachenote.server.payload.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
public class UserDoc {

    @Id
    private int id;

    private String username;

    private String password;
}

