package com.flowOps.flowOps_service.controller;

import com.flowOps.flowOps_service.common.response.APIResponse;
import com.flowOps.flowOps_service.common.utils.ResponseUtil;
import com.flowOps.flowOps_service.dto.projectMember.ProjectMemberRequestDto;
import com.flowOps.flowOps_service.dto.projectMember.ProjectMemberResponseDto;
import com.flowOps.flowOps_service.service.ProjectMemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project-members")
@Tag(name = "Project Member APIs", description = "Project Member Operations")
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;

    public ProjectMemberController(ProjectMemberService projectMemberService) {
        this.projectMemberService = projectMemberService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<ProjectMemberResponseDto>> createProjectMember(@Valid @RequestBody ProjectMemberRequestDto projectMemberRequestDto) {
        ProjectMemberResponseDto createdMember = projectMemberService.create(projectMemberRequestDto);
        return ResponseUtil.created(createdMember, "Project Member Created Successfully");

    }

    @GetMapping
    public ResponseEntity<APIResponse<List<ProjectMemberResponseDto>>> getAllProjectMembers() {
        List<ProjectMemberResponseDto> members = projectMemberService.getAll();
        return ResponseUtil.success(members, "Project Member Retrieved Successfully");

    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ProjectMemberResponseDto>> getProjectMemberById(@PathVariable Long id) {

        ProjectMemberResponseDto member = projectMemberService.getById(id);

        return ResponseUtil.success(member, "Project Member Retrieved Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ProjectMemberResponseDto>> updateProjectMember(@PathVariable Long id, @Valid @RequestBody ProjectMemberRequestDto dto) {

        ProjectMemberResponseDto updatedMember = projectMemberService.update(id, dto);

        return ResponseUtil.success(updatedMember, "Project Member Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteProjectMember(@PathVariable Long id) {

        projectMemberService.delete(id);

        return ResponseUtil.success(null, "Project Member Deleted Successfully");
    }


}
