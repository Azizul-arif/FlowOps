package com.flowOps.flowOps_service.service.impl;

import com.flowOps.flowOps_service.common.exception.ResourceNotFoundException;
import com.flowOps.flowOps_service.converter.projectMemberConverter.ProjectMemberConverter;
import com.flowOps.flowOps_service.dto.projectMember.ProjectMemberRequestDto;
import com.flowOps.flowOps_service.dto.projectMember.ProjectMemberResponseDto;
import com.flowOps.flowOps_service.entity.projectMember.ProjectMember;
import com.flowOps.flowOps_service.repository.ProjectmemberRepository;
import com.flowOps.flowOps_service.service.ProjectMemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    private final ProjectMemberConverter projectMemberConverter;
    private final ProjectmemberRepository projectmemberRepository;

    public ProjectMemberServiceImpl(ProjectMemberConverter projectMemberConverter, ProjectmemberRepository projectmemberRepository) {
        this.projectMemberConverter = projectMemberConverter;
        this.projectmemberRepository = projectmemberRepository;
    }

    @Override
    public ProjectMemberResponseDto create(ProjectMemberRequestDto projectMemberRequestDto) {
        ProjectMember projectMember = projectMemberConverter.toEntity(projectMemberRequestDto);
        ProjectMember savedMember = projectmemberRepository.save(projectMember);
        return projectMemberConverter.toDto(savedMember);
    }

    @Override
    public ProjectMemberResponseDto getById(Long id) {
        ProjectMember projectMember = projectmemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project Member Not Found"));

        return projectMemberConverter.toDto(projectMember);
    }

    @Override
    public List<ProjectMemberResponseDto> getAll() {
        List<ProjectMember> projectMembers=projectmemberRepository.findAll();
        return  projectMembers.stream()
                .map(projectMemberConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectMemberResponseDto update(Long id, ProjectMemberRequestDto projectMemberRequestDto) {
       ProjectMember existingMember=projectmemberRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("Project Member Not Found"));
       ProjectMember updateMember= projectMemberConverter.toEntity(projectMemberRequestDto);
       updateMember.setId(existingMember.getId());
       updateMember.setJoinedAt(existingMember.getJoinedAt());
       ProjectMember savedMember=projectmemberRepository.save(updateMember);
       return projectMemberConverter.toDto(savedMember);
    }

    @Override
    public void delete(Long id) {
        ProjectMember projectMember=projectmemberRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Project Member Not Found"));
        projectmemberRepository.delete(projectMember);

    }
}
