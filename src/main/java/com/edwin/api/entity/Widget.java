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
@Table(name = "WIDGET")
public class Widget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "minValue", length = 10)
    private Double minValue;

    @Column(name = "maxValue", length = 10)
    private Double maxValue;

    @Column(name = "color", length = 10)
    private String color;

    @Column(name = "date", length = 50)
    private String date;

    @PrePersist
    public void prepersist(){
        date = LocalDateTime.now().toString();
    }
}