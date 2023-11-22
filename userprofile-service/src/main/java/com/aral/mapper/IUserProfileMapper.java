package com.aral.mapper;

import com.aral.dto.response.FindUserProfileResponseDto;
import com.aral.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    FindUserProfileResponseDto findUserFromId(final UserProfile userProfile);
}
