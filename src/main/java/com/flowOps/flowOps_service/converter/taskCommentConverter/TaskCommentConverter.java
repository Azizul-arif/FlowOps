package com.flowOps.flowOps_service.converter.taskCommentConverter;

import com.flowOps.flowOps_service.dto.taskCommentDto.TaskCommentResponseDto;
import com.flowOps.flowOps_service.entity.taskComment.TaskComment;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TaskCommentConverter {
    public TaskCommentResponseDto entityToDto(TaskComment taskComment)
    {
        TaskCommentResponseDto dto=new TaskCommentResponseDto();
        dto.setId(taskComment.getId());
        dto.setTaskId(taskComment.getTask().getId());
        dto.setUserId(taskComment.getUser().getId());
        dto.setUserName(taskComment.getUser().getName());
        dto.setComment(taskComment.getComments());
        if (taskComment.getParentComment() != null) {
            dto.setParentCommentId(taskComment.getParentComment().getId());
        }

        dto.setCreatedAt(taskComment.getCreatedAt());

        dto.setReplies(
                taskComment.getReplies()
                        .stream()
                        .map(this::replyEntityToDto)
                        .collect(Collectors.toList())
        );

        return dto;
    }

    private TaskCommentResponseDto replyEntityToDto(TaskComment reply) {

        TaskCommentResponseDto dto = new TaskCommentResponseDto();
        dto.setId(reply.getId());
        dto.setTaskId(reply.getTask().getId());
        dto.setUserId(reply.getUser().getId());
        dto.setUserName(reply.getUser().getName());
        dto.setComment(reply.getComments());
        dto.setParentCommentId(reply.getParentComment().getId());
        dto.setCreatedAt(reply.getCreatedAt());
        return dto;
    }
}
