package com.cachenote.server.payload.entity;

import com.cachenote.server.security.UserRole;

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
    private UserRole userRole;


    public UserDoc(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
}

