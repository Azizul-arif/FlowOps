package com.flowOps.flowOps_service.controller;

import com.flowOps.flowOps_service.common.response.APIResponse;
import com.flowOps.flowOps_service.common.utils.ResponseUtil;
import com.flowOps.flowOps_service.dto.role.RoleDto;
import com.flowOps.flowOps_service.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Tag(name = "Role APIs", description = "Role management operations")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    public ResponseEntity<APIResponse<RoleDto>> save(@Valid @RequestBody RoleDto roleDto) {
        RoleDto saved = roleService.saveRole(roleDto);
        return ResponseUtil.created(saved, "Role Created Successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<RoleDto>>> getAllRoles() {
        List<RoleDto> roles = roleService.getAllRole();
        return ResponseUtil.success(roles, "Role Retrieved Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<RoleDto>> getRoleById(@PathVariable Long id) {
        RoleDto role = roleService.getRoleById(id);
        return ResponseUtil.success(role, "Role Retrieved Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<RoleDto>> updateRole(@PathVariable Long id, @Valid @RequestBody RoleDto roleDto) {
        RoleDto updatedRole = roleService.updateRole(id, roleDto);
        return ResponseUtil.success(updatedRole, "Role Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseUtil.success(null, "Role Deleted Successfully");
    }
}
