package com.flowOps.flowOps_service.service.impl;

import com.flowOps.flowOps_service.converter.designationConverter.DesignationConverter;
import com.flowOps.flowOps_service.dto.designation.DesignationDto;
import com.flowOps.flowOps_service.entity.department.Department;
import com.flowOps.flowOps_service.entity.designation.Designation;
import com.flowOps.flowOps_service.repository.DesignationRepository;
import com.flowOps.flowOps_service.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesignationServiceImpl implements DesignationService {
    @Autowired
    private DesignationRepository designationRepository;
    @Autowired
    private DesignationConverter designationConverter;

    @Override
    public DesignationDto saveDesignation(DesignationDto designationDto) {
        if (designationDto == null) {
            throw new RuntimeException("Designation data can not be null");
        }
        //convert dto to entity
        Designation designation = designationConverter.convertDtoToEntity(designationDto);
        Designation saveDesignation = designationRepository.save(designation);
        //convert entity to dto
        return designationConverter.convertEntityToDTO(saveDesignation);
    }

    @Override
    public List<DesignationDto> getAllDesignation() {

        return designationRepository.findAll()
                .stream()
                .map(designationConverter::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public void deleteDesignation(Long id) {

        if (!designationRepository.existsById(id)) {
            throw new RuntimeException("Designation not found with id: " + id);
        }
        designationRepository.deleteById(id);
    }

    @Override
    public DesignationDto updateDesignation(Long id, DesignationDto designationDto) {
        Designation existingDesignation = designationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designation not found with id: " + id));

        // update fields
        existingDesignation.setDesignationName(designationDto.getDesignationName());
        existingDesignation.setLevel(designationDto.getLevel());

        // save updated entity
        Designation updatedDesignation = designationRepository.save(existingDesignation);
        return designationConverter.convertEntityToDTO(updatedDesignation);
    }

    @Override
    public DesignationDto getDesignationById(Long id) {

        Designation designation = designationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Designation not found with id: " + id));

        return designationConverter.convertEntityToDTO(designation);
    }
}
