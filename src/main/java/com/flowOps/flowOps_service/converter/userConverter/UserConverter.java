package com.flowOps.flowOps_service.converter.userConverter;

import com.flowOps.flowOps_service.dto.department.DepartmentDto;
import com.flowOps.flowOps_service.dto.designation.DesignationDto;
import com.flowOps.flowOps_service.dto.role.RoleDto;
import com.flowOps.flowOps_service.dto.userDto.CurrentUserDto;
import com.flowOps.flowOps_service.dto.userDto.UserDto;
import com.flowOps.flowOps_service.entity.department.Department;
import com.flowOps.flowOps_service.entity.designation.Designation;
import com.flowOps.flowOps_service.entity.role.Role;
import com.flowOps.flowOps_service.entity.user.User;
import com.flowOps.flowOps_service.repository.DepartmentRepository;
import com.flowOps.flowOps_service.repository.DesignationRepository;
import com.flowOps.flowOps_service.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;
    private final RoleRepository roleRepository;

    public UserConverter(DepartmentRepository departmentRepository, DesignationRepository designationRepository, RoleRepository roleRepository) {
        this.designationRepository = designationRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
    }

    public User convertDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setStatus(userDto.getStatus());

        Department department = departmentRepository.findById(userDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Designation designation = designationRepository.findById(userDto.getDesignationId())
                .orElseThrow(() -> new RuntimeException("Designation not found"));
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(userDto.getRoleIds()));

        if (roles.isEmpty()) {
            throw new RuntimeException("Invalid Role selection");
        }
        user.setDepartment(department);
        user.setDesignation(designation);
        user.setRoles(roles);
        return user;
    }

    public UserDto convertEntityToDto(User user) {

        return UserDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .status(user.getStatus())
                .departmentId(user.getDepartment().getId())
                .designationId(user.getDesignation().getId())
                .roleIds(
                        user.getRoles()
                                .stream()
                                .map(Role::getId)
                                .collect(Collectors.toSet())
                )
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public CurrentUserDto convertEntityToCurrentUserDto(User user) {

        DepartmentDto departmentDto = DepartmentDto.builder()
                .departmentId(user.getDepartment().getId())
                .departmentName(user.getDepartment().getDepartmentName())
                .createdAt(user.getDepartment().getCreatedAt())
                .updatedAt(user.getDepartment().getUpdatedAt())
                .build();

        DesignationDto designationDto = DesignationDto.builder()
                .designation_id(user.getDesignation().getId())
                .designationName(user.getDesignation().getDesignationName())
                .level(user.getDesignation().getLevel())
                .createdAt(user.getDesignation().getCreatedAt())
                .updatedAt(user.getDesignation().getUpdatedAt())
                .build();

        Set<RoleDto> roleDtos = user.getRoles()
                .stream()
                .map(role -> RoleDto.builder()
                        .roleId(role.getId())
                        .roleName(role.getRoleName())
                        .createdAt(role.getCreatedAt())
                        .updatedAt(role.getUpdatedAt())
                        .build())
                .collect(Collectors.toSet());

        return CurrentUserDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .userStatus(user.getStatus())
                .department(departmentDto)
                .designation(designationDto)
                .roles(roleDtos)
                .build();
    }
}
