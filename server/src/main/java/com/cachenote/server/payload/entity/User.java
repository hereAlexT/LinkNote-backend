package com.cachenote.server.payload.entity;

import com.cachenote.server.security.UserRole;

import com.cachenote.server.utils.SnowflakeGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


// Add the missing Entity annotation
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user") // It's a good practice to use lowercase for table names
public class User {

    @Id
    @GenericGenerator(name = "SNOWFLAKE", type = SnowflakeGenerator.class)
    @GeneratedValue(
            generator = "SNOWFLAKE"
    )
    @Column(name="user_id")
    private Long id;

    @Column(name="username", nullable = false, unique = true) // Ensure username is unique
    private String username;

    @Column(name="password")
    private String password;

    // Assuming UserRole is an Enum and is correctly set up with JPA annotations
    @Enumerated(EnumType.STRING) // This is needed if UserRole is an Enum
    @Column(name="role")
    private UserRole userRole = UserRole.USER_NORMAL;

    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
}

