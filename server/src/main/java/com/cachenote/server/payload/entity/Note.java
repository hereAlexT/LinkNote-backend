package com.cachenote.server.payload.entity;


import com.cachenote.server.utils.SnowflakeGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name = "SNOWFLAKE", type = SnowflakeGenerator.class)
    @GeneratedValue(
            generator = "SNOWFLAKE"
    )
    @Column(name="note_id")
    private Long id;


    @Column(name="body")
    private String body;

//    @Column(name="cre_dt")
//    private LocalDateTime createdAt;
//
//
//    @Column(name="last_edit_dt")
//    private LocalDateTime lastEditTime;

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
