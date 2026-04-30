package com.flowOps.flowOps_service.converter.taskConverter;

import com.flowOps.flowOps_service.dto.taskDto.TaskDto;
import com.flowOps.flowOps_service.entity.project.Project;
import com.flowOps.flowOps_service.entity.task.Task;
import com.flowOps.flowOps_service.entity.user.User;
import com.flowOps.flowOps_service.repository.ProjectRepository;
import com.flowOps.flowOps_service.repository.TaskRepository;
import com.flowOps.flowOps_service.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskConverter(UserRepository userRepository,
                         TaskRepository taskRepository,
                         ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public Task convertDtoToEntity(TaskDto taskDto) {

        Task task;

        // Handle update vs create
        if (taskDto.getId() != null) {
            task = taskRepository.findById(taskDto.getId())
                    .orElseThrow(() -> new RuntimeException("Task not found"));
        } else {
            task = new Task();
        }

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setPriority(taskDto.getPriority());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(taskDto.getDueDate());

        // Null-safe assigned user
        if (taskDto.getAssignedToId() != null) {
            User assignedUser = userRepository.findById(taskDto.getAssignedToId())
                    .orElseThrow(() -> new RuntimeException("Assigned user not found"));
            task.setAssignedTo(assignedUser);
        }

        // Null-safe created user
        if (taskDto.getCreatedById() != null) {
            User createdUser = userRepository.findById(taskDto.getCreatedById())
                    .orElseThrow(() -> new RuntimeException("Creator user not found"));
            task.setCreatedBy(createdUser);
        }

        // Parent task
        if (taskDto.getParentTaskId() != null) {
            Task parent = taskRepository.findById(taskDto.getParentTaskId())
                    .orElseThrow(() -> new RuntimeException("Parent task not found"));
            task.setParentTask(parent);
        }

        // Project (mandatory)
        if (taskDto.getProjectId() != null) {
            Project project = projectRepository.findById(taskDto.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            task.setProject(project);
        } else {
            throw new RuntimeException("Project is required");
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
                .assignedToId(task.getAssignedTo() != null ? task.getAssignedTo().getId() : null)
                .createdById(task.getCreatedBy() != null ? task.getCreatedBy().getId() : null)
                .dueDate(task.getDueDate())
                .parentTaskId(task.getParentTask() != null ? task.getParentTask().getId() : null)
                .projectId(task.getProject() != null ? task.getProject().getId() : null)
                .build();
    }
}