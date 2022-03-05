package com.edwin.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SwitchDto {
    private Long id;
    private String name;
    private Boolean value;
    private String date;
}
