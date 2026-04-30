package com.flowOps.flowOps_service.dto.taskDto;

import com.flowOps.flowOps_service.common.enums.TaskPriority;
import com.flowOps.flowOps_service.common.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private TaskStatus status;
    private TaskPriority priority;

    private Long assignedToId;
    private Long createdById;
    private Long parentTaskId;
    private Long projectId;
    private LocalDate dueDate;
}
