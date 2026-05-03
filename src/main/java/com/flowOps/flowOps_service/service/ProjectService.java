package com.flowOps.flowOps_service.service;

import com.flowOps.flowOps_service.common.enums.ProjectStatus;
import com.flowOps.flowOps_service.dto.projectDto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProjectService {
    ProjectDto createProject(ProjectDto projectDto);

    ProjectDto getProjectById(Long id);

    List<ProjectDto> getAllProjects();

    ProjectDto updateProject(Long id, ProjectDto projectDto);

    void deleteProject(Long id);

    ProjectDto updateProjectStatus(Long id, ProjectStatus projectStatus);
}
