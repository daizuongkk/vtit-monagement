package com.daizuongkk.monagement.mapper;

import org.mapstruct.Mapper;

import com.daizuongkk.monagement.dto.response.ProfileResponse;
import com.daizuongkk.monagement.entity.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
  ProfileResponse toResponse(Profile profile);

}
