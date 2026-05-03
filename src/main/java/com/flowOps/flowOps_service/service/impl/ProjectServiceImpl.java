package com.flowOps.flowOps_service.service.impl;

import com.flowOps.flowOps_service.common.enums.ProjectStatus;
import com.flowOps.flowOps_service.common.exception.BadRequestException;
import com.flowOps.flowOps_service.common.exception.ResourceNotFoundException;
import com.flowOps.flowOps_service.converter.projectConverter.ProjectConverter;
import com.flowOps.flowOps_service.dto.projectDto.ProjectDto;
import com.flowOps.flowOps_service.entity.project.Project;
import com.flowOps.flowOps_service.repository.ProjectRepository;
import com.flowOps.flowOps_service.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;
    public ProjectServiceImpl(ProjectRepository projectRepository,ProjectConverter projectConverter)
    {
        this.projectRepository=projectRepository;
        this.projectConverter=projectConverter;
    }
    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project=projectConverter.convertDtoToEntity(projectDto);
        if(project.getStatus()==null)
        {
            project.setStatus(ProjectStatus.NOT_STARTED);
        }
        Project savedProject=projectRepository.save(project);
        return projectConverter.convertEntityToDto(savedProject);
    }

    @Override
    public ProjectDto getProjectById(Long id) {
       Project project=projectRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("project not found with id :" +id));
       return projectConverter.convertEntityToDto(project);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(projectConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto updateProject(Long id, ProjectDto projectDto) {

        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        // Business validation
        if (projectDto.getStartDate() != null && projectDto.getEndDate() != null) {
            if (projectDto.getEndDate().isBefore(projectDto.getStartDate())) {
                throw new BadRequestException("End date cannot be before start date");
            }
        }

        // Partial update (only update if value is provided)
        if (projectDto.getName() != null) {
            existingProject.setName(projectDto.getName());
        }

        if (projectDto.getDescription() != null) {
            existingProject.setDescription(projectDto.getDescription());
        }

        if (projectDto.getStartDate() != null) {
            existingProject.setStartDate(projectDto.getStartDate());
        }

        if (projectDto.getEndDate() != null) {
            existingProject.setEndDate(projectDto.getEndDate());
        }

        Project savedProject = projectRepository.save(existingProject);

        return projectConverter.convertEntityToDto(savedProject);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        // Business rule: prevent delete if tasks exist
        if (project.getTasks() != null && !project.getTasks().isEmpty()) {
            throw new BadRequestException("Cannot delete project with existing tasks");
        }
        projectRepository.delete(project);
    }

    @Override
    public ProjectDto updateProjectStatus(Long id, ProjectStatus newStatus) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        ProjectStatus currentStatus = project.getStatus();

        // Example: basic state transition validation
        if (currentStatus == ProjectStatus.COMPLETED) {
            throw new BadRequestException("Completed project status cannot be changed");
        }

        if (currentStatus == ProjectStatus.NOT_STARTED && newStatus == ProjectStatus.COMPLETED) {
            throw new BadRequestException("Project cannot be completed before starting");
        }
        project.setStatus(newStatus);
        Project updatedProject = projectRepository.save(project);
        return projectConverter.convertEntityToDto(updatedProject);
    }
}
