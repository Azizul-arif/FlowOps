package com.flowOps.flowOps_service.repository;

import com.flowOps.flowOps_service.entity.taskComment.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCommentRepository extends JpaRepository<TaskComment,Long> {
    List<TaskComment> findByTaskIdAndParentCommentIsNullOrderByCreatedAtAsc(Long taskId);

}
