package com.edwin.api.converter;

import com.edwin.api.dto.ThermometerDto;
import com.edwin.api.entity.Thermometer;

public class ThermometerConverter extends AbstractConverter<Thermometer, ThermometerDto>{
    @Override
    public Thermometer toEntity(ThermometerDto dto) {
        return Thermometer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .value(dto.getValue())
                .build();
    }

    @Override
    public ThermometerDto toDto(Thermometer entity) {
        return ThermometerDto.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .name(entity.getName())
                .value(entity.getValue())
                .build();
    }
}
