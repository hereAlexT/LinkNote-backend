package com.linknote.server.payload.entity;

import com.linknote.server.utils.SnowflakeIdGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class User {

    @Id
    @GenericGenerator(name = "SNOWFLAKE", type = SnowflakeIdGenerator.class)
    @GeneratedValue(
            generator = "SNOWFLAKE"
    )
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "pwd")
    private String password;

    @Column(name = "dis_name", nullable = false)
    private String displayName;

    @OneToMany(mappedBy = "user")
    private Set<Note> notes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @CreationTimestamp
    @Column(name = "reg_dt", nullable = false, updatable = false)
    private LocalDateTime registrationDate;


    /**
     * Used to register a user.
     *
     * @param email     email address
     * @param password  Encrypted password
     * @param userRoles A set of UserRole
     */
    public User(String email, String password, Set<UserRole> userRoles) {
        this.email = email;
        this.password = password;
        // Convert UserRole enum to Role entities
        this.roles = userRoles.stream().map(Role::new).collect(Collectors.toSet());
    }
}

