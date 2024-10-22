package org.cybersoft.bookingticketcinemabe.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface EntityMapper<D, E> {
    D toDTO(E entity);

    E toEntity(D dto);

    List<D> toDTOs(List<E> entities);

    List<E> toEntities(List<D> DTOs);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget E entity, D dto);
}
