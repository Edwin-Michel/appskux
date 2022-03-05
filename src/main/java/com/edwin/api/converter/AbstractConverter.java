package com.edwin.api.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E, D> {
    public abstract E toEntity(D dto);
    public abstract D toDto(E entity);
    public List<E> toEntityList(List<D> dtos){
        return dtos.stream().map( dto -> toEntity(dto)).collect(Collectors.toList());
    }

    public List<D> toDtoList(List<E> entities){
        return entities.stream().map( entity -> toDto(entity)).collect(Collectors.toList());
    }
}