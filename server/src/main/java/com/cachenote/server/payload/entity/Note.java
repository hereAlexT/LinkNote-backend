package com.cachenote.server.payload.entity;


import com.cachenote.server.utils.SnowflakeIdGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


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
    @Column(name="note_id")
    private Long id;


    @Column(name="body")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

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
