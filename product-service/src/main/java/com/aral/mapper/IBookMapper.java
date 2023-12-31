package com.aral.mapper;

import com.aral.dto.request.CreateBookRequestDto;
import com.aral.dto.request.UpdateBookRequestDto;
import com.aral.repository.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IBookMapper {
    IBookMapper INSTANCE = Mappers.getMapper(IBookMapper.class);

    Book fromCreateBookRequestDto(final CreateBookRequestDto dto);
    Book fromUpdateBookRequestDto(UpdateBookRequestDto dto, @MappingTarget Book book);



}
