package com.cachenote.server.payload.entity;


import com.cachenote.server.utils.SnowflakeIdGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        name = "note", uniqueConstraints = {@UniqueConstraint(columnNames = {"note_id"})}
)
public class Note {


    @Id
    @GenericGenerator(name = "SNOWFLAKE", type = SnowflakeIdGenerator.class)
    @GeneratedValue(
            generator = "SNOWFLAKE"
    )
    @Column(name = "note_id")
    private Long id;


    @Column(name = "body")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "cre_dt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_edit_dt")
    private LocalDateTime lastEditTime;


}
