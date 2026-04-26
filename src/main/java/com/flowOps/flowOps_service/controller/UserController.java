package com.flowOps.flowOps_service.controller;

import com.flowOps.flowOps_service.dto.userDto.UserDto;
import com.flowOps.flowOps_service.service.impl.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "User APIs", description = "User management operations")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService)
    {
        this.userService=userService;
    }
    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto)
    {
        UserDto createdUser=userService.createUser(userDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    // GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // GET ALL USERS
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
