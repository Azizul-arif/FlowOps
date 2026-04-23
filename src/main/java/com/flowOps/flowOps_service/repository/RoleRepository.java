package com.flowOps.flowOps_service.repository;

import com.flowOps.flowOps_service.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
