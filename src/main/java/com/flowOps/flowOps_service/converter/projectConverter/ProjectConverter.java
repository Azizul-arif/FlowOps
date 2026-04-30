package com.flowOps.flowOps_service.converter.projectConverter;

import com.flowOps.flowOps_service.dto.projectDto.ProjectDto;
import com.flowOps.flowOps_service.entity.project.Project;
import com.flowOps.flowOps_service.entity.user.User;
import com.flowOps.flowOps_service.repository.ProjectRepository;
import com.flowOps.flowOps_service.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public ProjectConverter(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public Project convertDtoToEntity(ProjectDto dto) {

        Project project;

        if (dto.getId() != null) {
            project = projectRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
        } else {
            project = new Project();
        }

        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());

        if (dto.getStatus() != null) {
            project.setStatus(dto.getStatus());
        }

        if (project.getId() == null) {
            if (dto.getCreatedBy() == null) {
                throw new RuntimeException("createdBy is required");
            }

            User user = userRepository.findById(dto.getCreatedBy())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            project.setCreatedBy(user);
        }

        return project;
    }

    public ProjectDto convertEntityToDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .status(project.getStatus())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .createdBy(project.getCreatedBy() != null ? project.getCreatedBy().getId() : null)
                .build();
    }
}
