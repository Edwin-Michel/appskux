package com.edwin.api.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "THERMOMETER")
public class Thermometer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "value", length = 10)
    private Double value;

    @Column(name = "date", length = 40)
    private String date;

    @PrePersist
    public void prepersist(){
        date = LocalDateTime.now().toString();
    }
}
