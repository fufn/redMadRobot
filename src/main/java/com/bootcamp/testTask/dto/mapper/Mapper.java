package com.bootcamp.testTask.dto.mapper;

public interface Mapper<T, G> {

    T toDto(G entity);

    G toEntity(T dto);


}