
package com.daizuongkk.monagement.mapper;

import org.mapstruct.Mapper;

import com.daizuongkk.monagement.dto.response.UserResponse;
import com.daizuongkk.monagement.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

  public UserResponse toUserResponse(User user);

}
