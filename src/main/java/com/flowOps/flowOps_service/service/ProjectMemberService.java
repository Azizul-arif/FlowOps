package com.flowOps.flowOps_service.service;

import com.flowOps.flowOps_service.dto.projectMember.ProjectMemberRequestDto;
import com.flowOps.flowOps_service.dto.projectMember.ProjectMemberResponseDto;

import java.util.List;

public interface ProjectMemberService {
    ProjectMemberResponseDto create(ProjectMemberRequestDto projectMemberRequestDto);
    ProjectMemberResponseDto getById(Long id);
    List<ProjectMemberResponseDto> getAll();
    ProjectMemberResponseDto update(Long id,ProjectMemberRequestDto projectMemberRequestDto);
    void delete(Long id);
}
