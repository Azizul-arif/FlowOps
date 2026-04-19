package com.flowOps.flowOps_service.controller;

import com.flowOps.flowOps_service.dto.department.DepartmentDto;
import com.flowOps.flowOps_service.dto.designation.DesignationDto;
import com.flowOps.flowOps_service.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/designation")
public class DesignationController {
    @Autowired
    private DesignationService designationService;

    @PostMapping("/save")
    public ResponseEntity<DesignationDto> save(@RequestBody DesignationDto designationDto)
    {
        DesignationDto saved=designationService.saveDesignation(designationDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<DesignationDto>> getAll() {
        List<DesignationDto> list = designationService.getAllDesignation();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesignationDto> getById(@PathVariable Long id) {

        DesignationDto dto = designationService.getDesignationById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DesignationDto> update(@PathVariable Long id, @RequestBody DesignationDto designationDto) {
        DesignationDto updated = designationService.updateDesignation(id, designationDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        designationService.deleteDesignation(id);
        return new ResponseEntity<>("Designation deleted successfully", HttpStatus.OK);
    }
}
