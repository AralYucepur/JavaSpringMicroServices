package com.aral.mapper;

import com.aral.dto.response.FindProductByIdResponseDto;
import com.aral.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IProductMapper {

    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

    FindProductByIdResponseDto fromProduct(final Product product);
//    Product fromFindProductByIdResponseDto(FindProductByIdResponseDto dto, @MappingTarget Product product);
}
