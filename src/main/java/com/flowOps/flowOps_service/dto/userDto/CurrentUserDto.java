package com.flowOps.flowOps_service.dto.userDto;

import com.flowOps.flowOps_service.common.enums.UserStatus;
import com.flowOps.flowOps_service.dto.department.DepartmentDto;
import com.flowOps.flowOps_service.dto.designation.DesignationDto;
import com.flowOps.flowOps_service.dto.role.RoleDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class CurrentUserDto {
    private Long userId;
    private String name;
    private String userName;
    private String email;
    private UserStatus userStatus;
    private DepartmentDto department;
    private DesignationDto designation;
    private Set<RoleDto> roles;


}
