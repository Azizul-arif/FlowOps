package com.flowOps.flowOps_service.service;

import com.flowOps.flowOps_service.dto.department.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> getAllDepartment();
    void deleteDepartment(Long id);
    DepartmentDto updateDepartment(Long id,DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long id);
}
