package com.flowOps.flowOps_service.dto.projectDto;

import com.flowOps.flowOps_service.common.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    @NotBlank(message = "Project name is required")
    private String name;

    private String description;

    private Long createdBy;

    private ProjectStatus status;

    private LocalDate startDate;

    private LocalDate endDate;

}
