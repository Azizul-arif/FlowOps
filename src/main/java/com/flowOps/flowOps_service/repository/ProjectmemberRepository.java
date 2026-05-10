package com.flowOps.flowOps_service.repository;

import com.flowOps.flowOps_service.entity.projectMember.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectmemberRepository extends JpaRepository<ProjectMember,Long> {
}
