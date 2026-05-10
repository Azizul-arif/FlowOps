package com.flowOps.flowOps_service.converter.projectMemberConverter;

import com.flowOps.flowOps_service.common.exception.ResourceNotFoundException;
import com.flowOps.flowOps_service.dto.projectMember.ProjectMemberRequestDto;
import com.flowOps.flowOps_service.dto.projectMember.ProjectMemberResponseDto;
import com.flowOps.flowOps_service.entity.project.Project;
import com.flowOps.flowOps_service.entity.projectMember.ProjectMember;
import com.flowOps.flowOps_service.entity.role.Role;
import com.flowOps.flowOps_service.entity.user.User;
import com.flowOps.flowOps_service.repository.ProjectRepository;
import com.flowOps.flowOps_service.repository.RoleRepository;
import com.flowOps.flowOps_service.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ProjectMemberConverter {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ProjectMemberConverter(ProjectRepository projectRepository,UserRepository userRepository,RoleRepository roleRepository)
    {
        this.projectRepository=projectRepository;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }

    public ProjectMember toEntity(ProjectMemberRequestDto projectMemberRequestDto)
    {
        Project project=projectRepository.findById(projectMemberRequestDto.getProjectId())
                .orElseThrow(()->new ResourceNotFoundException("project not found"));
        User user=userRepository.findById(projectMemberRequestDto.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        Role role=roleRepository.findById(projectMemberRequestDto.getRoleId())
                .orElseThrow(()->new ResourceNotFoundException("Role not found"));
        ProjectMember member=new ProjectMember();
        member.setProject(project);
        member.setUser(user);
        member.setRole(role);
        member.setAddedBy(user);
        return member;
    }

    public ProjectMemberResponseDto toDto(ProjectMember entity) {

        ProjectMemberResponseDto dto = new ProjectMemberResponseDto();

        dto.setId(entity.getId());

        dto.setProjectId(entity.getProject().getId());
        dto.setProjectName(entity.getProject().getName());

        dto.setUserId(entity.getUser().getId());
        dto.setUserName(entity.getUser().getName());

        dto.setRoleId(entity.getRole().getId());
        dto.setRoleName(entity.getRole().getRoleName());

        dto.setJoinedAt(entity.getJoinedAt());
        dto.setRemovedAt(entity.getRemovedAt());


        dto.setAddedBy(entity.getAddedBy() != null ? entity.getAddedBy().getId() : null);

        return dto;
    }
}
