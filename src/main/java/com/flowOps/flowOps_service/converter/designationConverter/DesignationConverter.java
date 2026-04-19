package com.flowOps.flowOps_service.converter.designationConverter;

import com.flowOps.flowOps_service.dto.designation.DesignationDto;
import com.flowOps.flowOps_service.entity.designation.Designation;
import org.springframework.stereotype.Component;

@Component
public class DesignationConverter {
    public DesignationDto convertEntityToDTO(Designation designation)
    {
        if(designation==null)
        {
            return null;
        }
        DesignationDto dto=new DesignationDto();
        dto.setDesignation_id(designation.getId());
        dto.setDesignationName(designation.getDesignationName());
        dto.setLevel(designation.getLevel());
        dto.setCreatedAt(designation.getCreatedAt());
        dto.setUpdatedAt(designation.getUpdatedAt());
        return dto;

    }

    public Designation convertDtoToEntity(DesignationDto designationDto)
    {
        if(designationDto==null)
        {
            return null;
        }
        Designation designationEntity=new Designation();
        designationEntity.setId(designationEntity.getId());
        designationEntity.setDesignationName(designationDto.getDesignationName());
        designationEntity.setLevel(designationDto.getLevel());
        designationEntity.setCreatedAt(designationDto.getCreatedAt());
        designationEntity.setCreatedAt(designationDto.getCreatedAt());
        return designationEntity;

    }
}
