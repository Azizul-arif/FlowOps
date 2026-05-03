package com.flowOps.flowOps_service.service;

import com.flowOps.flowOps_service.dto.userDto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    void deleteUser(Long id);
}
