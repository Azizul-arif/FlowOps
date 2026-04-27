package com.flowOps.flowOps_service.service.impl;

import com.flowOps.flowOps_service.common.enums.TaskStatus;
import com.flowOps.flowOps_service.converter.taskConverter.TaskConverter;
import com.flowOps.flowOps_service.dto.taskDto.TaskDto;
import com.flowOps.flowOps_service.entity.task.Task;
import com.flowOps.flowOps_service.entity.user.User;
import com.flowOps.flowOps_service.repository.TaskRepository;
import com.flowOps.flowOps_service.repository.UserRepository;
import com.flowOps.flowOps_service.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository,TaskConverter taskConverter,UserRepository userRepository)
    {
        this.taskRepository=taskRepository;
        this.taskConverter=taskConverter;
        this.userRepository=userRepository;
    }

    // CREATE TASK
    @Override
    public TaskDto createTask(TaskDto dto) {

        Task task = taskConverter.convertDtoToEntity(dto);

        // createdBy will come from logged-in user (future)
        // TEMP (until auth added)
        User creator = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Creator not found"));

        task.setCreatedBy(creator);
        Task savedTask = taskRepository.save(task);
        return taskConverter.convertEntityToDto(savedTask);
    }

    //GET BY ID
    @Override
    public TaskDto getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return taskConverter.convertEntityToDto(task);
    }

    // GET ALL
    @Override
    public List<TaskDto> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(taskConverter::convertEntityToDto)
                .toList();
    }

    // UPDATE TASK (FULL UPDATE)
    @Override
    public TaskDto updateTask(Long id, TaskDto dto) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // update fields manually (controlled update)
        existingTask.setTitle(dto.getTitle());
        existingTask.setDescription(dto.getDescription());
        existingTask.setPriority(dto.getPriority());
        existingTask.setDueDate(dto.getDueDate());

        // update assigned user
        if (dto.getAssignedToId() != null) {
            User assignedUser = userRepository.findById(dto.getAssignedToId())
                    .orElseThrow(() -> new RuntimeException("Assigned user not found"));
            existingTask.setAssignedTo(assignedUser);
        }

        Task updatedTask = taskRepository.save(existingTask);

        return taskConverter.convertEntityToDto(updatedTask);
    }

    //DELETE TASK
    @Override
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
    }

    // UPDATE STATUS (WITH VALIDATION)
    @Override
    public TaskDto updateTaskStatus(Long id, TaskStatus newStatus) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // 🔥 STATUS FLOW VALIDATION
        validateStatusTransition(task.getStatus(), newStatus);
        task.setStatus(newStatus);
        Task updatedTask = taskRepository.save(task);
        return taskConverter.convertEntityToDto(updatedTask);
    }

    // BUSINESS LOGIC (IMPORTANT)
    private void validateStatusTransition(TaskStatus current, TaskStatus next) {

        if (current == TaskStatus.TODO && next == TaskStatus.DONE) {
            throw new RuntimeException("Task must go through IN_PROGRESS first");
        }

        if (current == TaskStatus.DONE && next == TaskStatus.TODO) {
            throw new RuntimeException("Cannot move DONE back to TODO");
        }

        // allow reopen
        if (current == TaskStatus.DONE && next == TaskStatus.IN_PROGRESS) {
            return;
        }
    }
}
