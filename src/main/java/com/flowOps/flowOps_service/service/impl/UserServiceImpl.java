package com.flowOps.flowOps_service.service.impl;

import com.flowOps.flowOps_service.converter.userConverter.UserConverter;
import com.flowOps.flowOps_service.dto.userDto.UserDto;
import com.flowOps.flowOps_service.entity.user.User;
import com.flowOps.flowOps_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserConverter userConverter;
    private final UserRepository userRepository;

    public UserServiceImpl(UserConverter userConverter, UserRepository userRepository) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email Already exists");
        }
        User user = userConverter.convertDtoToEntity(userDto);
        User saved = userRepository.save(user);
        return userConverter.convertEntityToDto(saved);
    }

    // GET USER BY ID
    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userConverter.convertEntityToDto(user);
    }

    // GET ALL USERS
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // DELETE USER
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }
}
