package com.flowOps.flowOps_service.repository;

import com.flowOps.flowOps_service.entity.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
