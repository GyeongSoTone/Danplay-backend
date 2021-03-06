package com.danplay.server.user.dto;

import com.danplay.server.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User reqToE(SignUpRequest signUpRequest);

    UserResponse eToRes(User user);
}
