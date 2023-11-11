package com.cachenote.server.payload.entity;

import com.cachenote.server.security.UserRole;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




// Add the missing Entity annotation
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users") // It's a good practice to use lowercase for table names
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="username", nullable = false, unique = true) // Ensure username is unique
    private String username;

    @Column(name="password")
    private String password;

    // Assuming UserRole is an Enum and is correctly set up with JPA annotations
    @Enumerated(EnumType.STRING) // This is needed if UserRole is an Enum
    private UserRole userRole = UserRole.USER_NORMAL;

    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
}

