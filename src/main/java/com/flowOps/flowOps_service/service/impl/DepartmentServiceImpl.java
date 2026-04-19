package com.flowOps.flowOps_service.service.impl;

import com.flowOps.flowOps_service.converter.departmentConverter.DepartmentConverter;
import com.flowOps.flowOps_service.dto.department.DepartmentDto;
import com.flowOps.flowOps_service.entity.department.Department;
import com.flowOps.flowOps_service.repository.DepartmentRepository;
import com.flowOps.flowOps_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentConverter departmentConverter;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //Dto to entity
        Department department = departmentConverter.convertDtoToEntity(departmentDto);
        //save To DB
        Department saved = departmentRepository.save(department);

        //entity to dto
        return departmentConverter.convertEntityToDto(saved);
    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(departmentConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        departmentRepository.delete(department);
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {

        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        // update fields
        existingDepartment.setDepartmentName(departmentDto.getDepartmentName());

        // save updated entity
        Department updatedDepartment = departmentRepository.save(existingDepartment);

        return departmentConverter.convertEntityToDto(updatedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        return departmentConverter.convertEntityToDto(department);
    }
}
