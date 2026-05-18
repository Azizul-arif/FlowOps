package com.flowOps.flowOps_service.repository;

import com.flowOps.flowOps_service.entity.taskComment.TaskComment;
import com.flowOps.flowOps_service.entity.taskCommentMention.TaskCommentMention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskCommentMentionRepository  extends JpaRepository<TaskCommentMention,Long> {
    //List<TaskComment> findByTaskIdAndParentCommentIsNullOrderByCreatedAtAsc(Long taskId);
}
