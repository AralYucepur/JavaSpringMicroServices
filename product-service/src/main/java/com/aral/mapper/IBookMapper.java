package com.aral.mapper;

import com.aral.dto.request.CreateBookRequestDto;
import com.aral.repository.entity.Book;
import com.aral.repository.entity.BookType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IBookMapper {
    IBookMapper INSTANCE = Mappers.getMapper(IBookMapper.class);

//    default BookType mapStringToBookType(String bookType) {
//        return BookType.valueOf(bookType);
//    }
//    @Mapping(target = "type", source = "dto.type")
    Book fromCreateBookRequestDto(final CreateBookRequestDto dto);


}
