package com.edwin.api.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "SWITCH")
public class Switch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Boolean value;

    @Column(name = "date")
    private String date;

    @PrePersist
    public void setDefault(){
        date = LocalDateTime.now().toString();
    }
}
