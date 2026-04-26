package com.flowOps.flowOps_service.converter.userConverter;

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
}
