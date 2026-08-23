package com.daizuongkk.monagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.daizuongkk.monagement.dto.response.ProfileResponse;
import com.daizuongkk.monagement.entity.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
  @Mapping(target = "userId", source = "user.id")
  ProfileResponse toResponse(Profile profile);

}
