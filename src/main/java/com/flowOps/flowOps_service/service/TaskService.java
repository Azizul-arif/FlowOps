package com.flowOps.flowOps_service.service;

import com.flowOps.flowOps_service.common.enums.TaskStatus;
import com.flowOps.flowOps_service.dto.taskDto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);

    TaskDto getTaskById(Long id);

    List<TaskDto> getAllTasks();

    TaskDto updateTask(Long id, TaskDto taskDto);

    void deleteTask(Long id);

    TaskDto updateTaskStatus(Long id, TaskStatus status);
}
