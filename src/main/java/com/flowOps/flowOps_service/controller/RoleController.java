package com.flowOps.flowOps_service.controller;

import com.flowOps.flowOps_service.dto.role.RoleDto;
import com.flowOps.flowOps_service.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@Tag(name = "Role APIs", description = "Role management operations")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    public ResponseEntity<RoleDto> save(@RequestBody RoleDto roleDto)
    {
        RoleDto saved=roleService.saveRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
