package com.aral.mapper;

import com.aral.dto.response.FindProductByIdResponseDto;
import com.aral.dto.response.SaleResponseDto;
import com.aral.repository.entity.Sales;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ISalesMapper {

    ISalesMapper INSTANCE = Mappers.getMapper(ISalesMapper.class);

    Sales fromProductReturnDto(final FindProductByIdResponseDto dto);
    SaleResponseDto fromSales(final Sales sales);
}
