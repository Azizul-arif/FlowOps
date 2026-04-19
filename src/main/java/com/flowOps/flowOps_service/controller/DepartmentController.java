package com.flowOps.flowOps_service.controller;

import com.flowOps.flowOps_service.dto.department.DepartmentDto;
import com.flowOps.flowOps_service.entity.department.Department;
import com.flowOps.flowOps_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/save")
    public ResponseEntity<DepartmentDto> save(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto saved = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDto>> getAll() {
        List<DepartmentDto> list = departmentService.getAllDepartment();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getById(@PathVariable Long id) {

        DepartmentDto dto = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> update(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) {
        DepartmentDto updated = departmentService.updateDepartment(id, departmentDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>("Department deleted successfully", HttpStatus.OK);
    }


}
