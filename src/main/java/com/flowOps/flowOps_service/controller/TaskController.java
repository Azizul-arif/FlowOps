package com.flowOps.flowOps_service.controller;

import com.flowOps.flowOps_service.common.response.APIResponse;
import com.flowOps.flowOps_service.common.utils.ResponseUtil;
import com.flowOps.flowOps_service.dto.taskDto.TaskDto;
import com.flowOps.flowOps_service.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Task APIs", description = "Task Operations")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService)
    {
        this.taskService=taskService;
    }
    @PostMapping()
    public ResponseEntity<APIResponse<TaskDto>> createTask(@Valid @RequestBody TaskDto taskDto)
    {
        TaskDto createdTask=taskService.createTask(taskDto);
        return ResponseUtil.created(createdTask,"Task Created Successfully");
    }
}
