package com.flowOps.flowOps_service.service;

import com.flowOps.flowOps_service.dto.role.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto saveRole(RoleDto roleDto);
    List<RoleDto> getAllRole();
    void deleteRole(long roleId);
    RoleDto updateRole(long id,RoleDto roleDto);
    RoleDto getRoleById(long id);
}
