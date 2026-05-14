package com.flowOps.flowOps_service.dto.taskCommentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCommentResponseDto {
    private Long id;
    private Long taskId;
    private Long userId;
    private String userName;
    private String comment;
    private Long parentCommentId;
    private LocalDateTime createdAt;
    private List<TaskCommentResponseDto> replies = new ArrayList<>();
}
