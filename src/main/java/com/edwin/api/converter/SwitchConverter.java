package com.edwin.api.converter;

import com.edwin.api.dto.SwitchDto;
import com.edwin.api.entity.Switch;

public class SwitchConverter extends AbstractConverter<Switch, SwitchDto>{
    @Override
    public Switch toEntity(SwitchDto dto) {
        return Switch.builder()
                .id(dto.getId())
                .name(dto.getName())
                .date(dto.getDate())
                .value(dto.getValue())
                .build();
    }

    @Override
    public SwitchDto toDto(Switch entity) {
        return SwitchDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .date(entity.getDate())
                .value(entity.getValue())
                .build();
    }
}
