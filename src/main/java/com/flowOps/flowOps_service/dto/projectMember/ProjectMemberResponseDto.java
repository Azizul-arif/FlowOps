package com.flowOps.flowOps_service.dto.projectMember;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberResponseDto {
    private Long id;
    private Long projectId;
    private String projectName;
    private Long userId;
    private String userName;
    private Long roleId;
    private String roleName;
    private LocalDateTime joinedAt;
    private LocalDateTime removedAt;

    private Long addedBy;
}
