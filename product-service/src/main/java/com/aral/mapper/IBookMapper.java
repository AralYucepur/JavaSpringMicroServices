package com.aral.mapper;

import com.aral.dto.request.CreateBookRequestDto;
import com.aral.repository.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IBookMapper {
    IBookMapper INSTANCE = Mappers.getMapper(IBookMapper.class);

    Book fromCreateBookRequestDto(final CreateBookRequestDto dto);


}
