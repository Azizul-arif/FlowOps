package com.flowOps.flowOps_service.repository;

import com.flowOps.flowOps_service.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
