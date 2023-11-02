package com.aral.mapper;

import com.aral.dto.request.RegisterRequestDto;
import com.aral.dto.response.RegisterResponseDto;
import com.aral.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAuthMapper {
        IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

        Auth fromRegisterRequestDto(final RegisterRequestDto dto);
        @Mapping(source = "id", target = "authid")
        RegisterResponseDto fromAuth(final Auth auth);

}
