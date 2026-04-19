package com.flowOps.flowOps_service.service;

import com.flowOps.flowOps_service.dto.department.DepartmentDto;
import com.flowOps.flowOps_service.dto.designation.DesignationDto;

import java.util.List;

public interface DesignationService {
    DesignationDto saveDesignation(DesignationDto designationDto);
    List<DesignationDto> getAllDesignation();
    void deleteDesignation(Long id);
    DesignationDto updateDesignation(Long id,DesignationDto designationDto);
    DesignationDto getDesignationById(Long id);
}
