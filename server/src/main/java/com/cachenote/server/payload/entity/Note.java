package com.cachenote.server.payload.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        name = "Note", uniqueConstraints = {@UniqueConstraint(columnNames = {"note_id"})}
)
public class Note {


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
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
