package com.flowOps.flowOps_service.service.impl;

import com.flowOps.flowOps_service.converter.taskCommentConverter.TaskCommentConverter;
import com.flowOps.flowOps_service.dto.taskCommentDto.CreateTaskCommentDto;
import com.flowOps.flowOps_service.dto.taskCommentDto.TaskCommentResponseDto;
import com.flowOps.flowOps_service.entity.task.Task;
import com.flowOps.flowOps_service.entity.taskComment.TaskComment;
import com.flowOps.flowOps_service.entity.taskCommentMention.TaskCommentMention;
import com.flowOps.flowOps_service.entity.user.User;
import com.flowOps.flowOps_service.repository.TaskCommentMentionRepository;
import com.flowOps.flowOps_service.repository.TaskCommentRepository;
import com.flowOps.flowOps_service.repository.TaskRepository;
import com.flowOps.flowOps_service.repository.UserRepository;
import com.flowOps.flowOps_service.service.TaskCommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional

public class TaskCommentServiceImpl implements TaskCommentService {
    private final TaskRepository taskRepository;
    private final TaskCommentRepository taskCommentRepository;
    private final TaskCommentMentionRepository taskCommentMentionRepository;
    private final UserRepository userRepository;
    private final TaskCommentConverter taskCommentConverter;

    @Override
    public TaskCommentResponseDto createComment(CreateTaskCommentDto createTaskCommentDto, Long loggedInuserId) {
        Task task = taskRepository.findById(createTaskCommentDto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task Not Found"));
        User user = userRepository.findById(loggedInuserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        TaskComment parentComment = null;
        if (createTaskCommentDto.getParentCommentId() != null) {
            parentComment = taskCommentRepository.findById(createTaskCommentDto.getParentCommentId())
                    .orElseThrow(() -> new RuntimeException("Parent Comment Not Found"));
            if (parentComment.getParentComment() != null) {
                throw new RuntimeException("only 1 level reply is allowed");
            }
            if (!parentComment.getTask().getId().equals(task.getId())) {
                throw new RuntimeException("Parent comment does not belong to this task");
            }
        }

        TaskComment taskComment = new TaskComment();
        taskComment.setComments(createTaskCommentDto.getComment());
        taskComment.setUser(user);
        taskComment.setParentComment(parentComment);
        TaskComment savedComment = taskCommentRepository.save(taskComment);

        if (createTaskCommentDto.getMentionedUserIds() != null && !createTaskCommentDto.getMentionedUserIds().isEmpty()) {

            List<User> mentionedUsers = userRepository.findAllById(createTaskCommentDto.getMentionedUserIds());

            List<TaskCommentMention> mentions = mentionedUsers.stream()
                    .map(mentionedUser -> {

                        TaskCommentMention mention = new TaskCommentMention();
                        mention.setComment(savedComment);
                        mention.setMentionedUser(mentionedUser);
                        return mention;
                    }).collect(Collectors.toList());

            taskCommentMentionRepository.saveAll(mentions);
        }

        return taskCommentConverter.entityToDto(savedComment);
    }

    @Override
    public List<TaskCommentResponseDto> getTaskComments(Long taskId) {
        List<TaskComment> comments = taskCommentRepository
                .findByTaskIdAndParentCommentIsNullOrderByCreatedAtAsc(taskId);

        return comments.stream()
                .map(taskCommentConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId, Long loggedInUserId) {
        TaskComment comment = taskCommentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(loggedInUserId)) {
            throw new RuntimeException("You are not authorized to delete this comment");
        }

        taskCommentRepository.delete(comment);

    }
}
