package com.flowOps.flowOps_service.controller;

import com.flowOps.flowOps_service.common.response.APIResponse;
import com.flowOps.flowOps_service.common.utils.ResponseUtil;
import com.flowOps.flowOps_service.dto.taskDto.TaskDto;
import com.flowOps.flowOps_service.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping()
    public  ResponseEntity<APIResponse<List<TaskDto>>>getAllTasks()
    {
        List<TaskDto> tasks=taskService.getAllTasks();
        return  ResponseUtil.success(tasks,"Task Retrieved Successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<TaskDto>> getTaskById(@PathVariable Long id)
    {
        TaskDto task=taskService.getTaskById(id);
        return  ResponseUtil.success(task,"Task Retrieved Successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<TaskDto>>updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto)
    {
        TaskDto updatedTask=taskService.updateTask(id,taskDto);
        return ResponseUtil.success(updatedTask,"Task Updated Successfully");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return ResponseUtil.success(null,"Task Deleted Successfully");
    }
}

