package com.flowOps.flowOps_service.converter.departmentConverter;

import com.flowOps.flowOps_service.dto.department.DepartmentDto;
import com.flowOps.flowOps_service.entity.department.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter {
    public DepartmentDto convertEntityToDto(Department department)
    {
        return DepartmentDto.builder()
                .departmentId(department.getId())
                .departmentName(department.getDepartmentName())
                .createdAt(department.getCreatedAt())
                .updatedAt(department.getUpdatedAt())
                .build();
    }

    public Department convertDtoToEntity(DepartmentDto departmentDto)
    {
        Department department =new Department();
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setId(departmentDto.getDepartmentId());
        department.setCreatedAt(departmentDto.getCreatedAt());
        department.setUpdatedAt(departmentDto.getUpdatedAt());
        return department;
    }

}
