package com.zsc.edu.bill.common.mapstruct;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<D, E> {
    D toDto(E entity);
    E toEntity(D dto);
    List<D> toDto(List<E> entityList);
    List<E> toEntity(List<D> dtoList);

    /**
     * 更新实体类
     * @param dto
     * @param entity
     */
    void convert(D dto, @MappingTarget E entity);
}
