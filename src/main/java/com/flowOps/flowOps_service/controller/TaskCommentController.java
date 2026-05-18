package com.flowOps.flowOps_service.controller;

import com.flowOps.flowOps_service.common.response.APIResponse;
import com.flowOps.flowOps_service.common.utils.ResponseUtil;
import com.flowOps.flowOps_service.dto.taskCommentDto.CreateTaskCommentDto;
import com.flowOps.flowOps_service.dto.taskCommentDto.TaskCommentResponseDto;
import com.flowOps.flowOps_service.service.TaskCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-comments")
public class TaskCommentController {
    private final TaskCommentService taskCommentService;
    public TaskCommentController(TaskCommentService taskCommentService)
    {
        this.taskCommentService=taskCommentService;
    }

    public ResponseEntity<APIResponse<TaskCommentResponseDto>> createComment(@RequestBody CreateTaskCommentDto createTaskCommentDto)
    {
        Long loggedinuserId=1L;
        TaskCommentResponseDto createdComment=taskCommentService.createComment(createTaskCommentDto,loggedinuserId);
        return ResponseUtil.created(createdComment,"comment posted");
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<APIResponse<List<TaskCommentResponseDto>>> getTaskComments(@PathVariable Long taskId) {

        List<TaskCommentResponseDto> comments = taskCommentService.getTaskComments(taskId);
        return ResponseUtil.success(comments,"Comments Retrieved Successfully");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<APIResponse<String>> deleteComment(@PathVariable Long commentId) {

        Long loggedInUserId = 1L;
        taskCommentService.deleteComment(commentId, loggedInUserId);
        return ResponseUtil.success(null,"Comment Deleted Successfully");
    }
}
