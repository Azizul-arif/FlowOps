package com.flowOps.flowOps_service.service.impl;

import com.flowOps.flowOps_service.common.exception.BadRequestException;
import com.flowOps.flowOps_service.converter.roleConverter.RoleConverter;
import com.flowOps.flowOps_service.dto.role.RoleDto;
import com.flowOps.flowOps_service.entity.designation.Designation;
import com.flowOps.flowOps_service.entity.role.Role;
import com.flowOps.flowOps_service.repository.RoleRepository;
import com.flowOps.flowOps_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleConverter roleConverter;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDto saveRole(RoleDto roleDto) {
        if (roleDto == null) {
            throw new BadRequestException("Role can not be null");
        }
        //convert dto to entity
        Role role = roleConverter.convertDTotoEntity(roleDto);
        //save into db
        Role savedRole = roleRepository.save(role);
        //convert entity to dto
        return roleConverter.convertEntityToDTO(savedRole);
    }

    @Override
    public List<RoleDto> getAllRole() {
        return roleRepository.findAll()
                .stream()
                .map(roleConverter::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRole(long roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new RuntimeException("Department id not found for:" + roleId);

        }
        roleRepository.deleteById(roleId);

    }

    @Override
    public RoleDto updateRole(long id, RoleDto roleDto) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        // update fields
        existingRole.setRoleName(roleDto.getRoleName());
        // save updated entity
        Role updateRole = roleRepository.save(existingRole);
        return roleConverter.convertEntityToDTO(updateRole);
    }

    @Override
    public RoleDto getRoleById(long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        return roleConverter.convertEntityToDTO(role);
    }
}
