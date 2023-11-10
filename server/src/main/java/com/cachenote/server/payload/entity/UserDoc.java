package com.cachenote.server.payload.entity;

import com.cachenote.server.security.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private Role role;


    public UserDoc(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

