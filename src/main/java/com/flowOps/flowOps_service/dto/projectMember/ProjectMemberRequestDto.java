package com.flowOps.flowOps_service.dto.projectMember;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberRequestDto {
    private Long projectId;
    private Long userId;
    private Long roleId;
    private Long addedBy;

}
