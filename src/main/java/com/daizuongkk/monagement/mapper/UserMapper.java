
package com.daizuongkk.monagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.daizuongkk.monagement.dto.response.UserResponse;
import com.daizuongkk.monagement.entity.User;

@Mapper(componentModel = "spring", uses = ProfileMapper.class)
public interface UserMapper {

  @Mapping(source = "profile", target = "profile")
  public UserResponse toResponse(User user);

}
