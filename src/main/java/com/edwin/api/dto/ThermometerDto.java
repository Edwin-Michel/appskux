package com.edwin.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ThermometerDto {
    private Long id;
    private String name;
    private Double value;
    private String date;
}
