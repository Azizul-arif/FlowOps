package com.flowOps.flowOps_service.converter.roleConverter;

import com.flowOps.flowOps_service.dto.role.RoleDto;
import com.flowOps.flowOps_service.entity.role.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    public RoleDto convertEntityToDTO(Role role)
    {
        return RoleDto.builder().
                roleId(role.getId())
                .roleName(role.getRoleName())
                .createdAt(role.getCreatedAt())
                .updatedAt(role.getUpdatedAt())
                .build();
    }
    public Role convertDTotoEntity(RoleDto roleDto)
    {
        Role role=new Role();
        role.setId(roleDto.getRoleId());
        role.setRoleName(roleDto.getRoleName());
        role.setCreatedAt(roleDto.getCreatedAt());
        role.setUpdatedAt(roleDto.getUpdatedAt());
        return role;
    }
}
