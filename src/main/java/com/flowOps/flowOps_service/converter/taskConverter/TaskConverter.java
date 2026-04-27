package com.flowOps.flowOps_service.converter.taskConverter;

import com.flowOps.flowOps_service.dto.taskDto.TaskDto;
import com.flowOps.flowOps_service.entity.task.Task;
import com.flowOps.flowOps_service.entity.user.User;
import com.flowOps.flowOps_service.repository.TaskRepository;
import com.flowOps.flowOps_service.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {
    private UserRepository userRepository;
    private TaskRepository taskRepository;
    public TaskConverter(UserRepository userRepository,TaskRepository taskRepository)
    {
        this.userRepository=userRepository;
        this.taskRepository=taskRepository;
    }
    public Task convertDtoToEntity(TaskDto taskDto)
    {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setDueDate(taskDto.getDueDate());
        User assignedUser=userRepository.findById(taskDto.getAssignedToId())
                .orElseThrow(()->new RuntimeException("Assigned user not found"));
        User createdUser=userRepository.findById(taskDto.getCreatedById())
                .orElseThrow(()->new RuntimeException("Creator user not found"));
        task.setCreatedBy(createdUser);
        task.setAssignedTo(assignedUser);
        task.setStatus(taskDto.getStatus());
        if (taskDto.getParentTaskId() != null) {
            Task parent = taskRepository.findById(taskDto.getParentTaskId())
                    .orElseThrow(() -> new RuntimeException("Parent task not found"));
            task.setParentTask(parent);
        }
        return task;

    }
    public TaskDto convertEntityToDto(Task task) {

        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .assignedToId(
                        task.getAssignedTo() != null ? task.getAssignedTo().getId() : null
                )
                .createdById(
                        task.getCreatedBy() != null ? task.getCreatedBy().getId() : null
                )
                .dueDate(task.getDueDate())
                .parentTaskId(
                        task.getParentTask() != null ? task.getParentTask().getId() : null
                )
                .build();
    }
}
