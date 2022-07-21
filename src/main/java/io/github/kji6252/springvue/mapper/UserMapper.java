package io.github.kji6252.springvue.mapper;

import io.github.kji6252.springvue.controller.vm.UserInfoVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserInfoVM userToVM(UserDetails userDetails);
}
