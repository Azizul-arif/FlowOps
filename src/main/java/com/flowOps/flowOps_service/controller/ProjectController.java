package com.flowOps.flowOps_service.controller;

import com.flowOps.flowOps_service.common.response.APIResponse;
import com.flowOps.flowOps_service.common.utils.ResponseUtil;
import com.flowOps.flowOps_service.dto.projectDto.ProjectDto;
import com.flowOps.flowOps_service.dto.taskDto.TaskDto;
import com.flowOps.flowOps_service.service.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@Tag(name = "Project APIs", description = "Project Operations")
public class ProjectController {
    private final ProjectService projectService;
    public ProjectController(ProjectService projectService)
    {
        this.projectService=projectService;
    }
    //create project
    @PostMapping
    public ResponseEntity<APIResponse<ProjectDto>> createTask(@Valid @RequestBody ProjectDto projectDto)
    {
        ProjectDto createdProject=projectService.createProject(projectDto);
        return ResponseUtil.created(createdProject,"Project Created Successfully");
    }

}
