package com.flowOps.flowOps_service.service;

import com.flowOps.flowOps_service.dto.taskCommentDto.CreateTaskCommentDto;
import com.flowOps.flowOps_service.dto.taskCommentDto.TaskCommentResponseDto;

import java.util.List;

public interface TaskCommentService {
    TaskCommentResponseDto createComment(CreateTaskCommentDto createTaskCommentDto,Long loggedInuserId);
    List<TaskCommentResponseDto> getTaskComments(Long taskId);
    void deleteComment(Long commentId,Long loggedInUserId);

}
