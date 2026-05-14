package com.flowOps.flowOps_service.dto.taskCommentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskCommentDto {
    private Long taskId;
    private Long parentCommentId;
    private String comment;
    private List<Long> mentionedUserIds;
}
