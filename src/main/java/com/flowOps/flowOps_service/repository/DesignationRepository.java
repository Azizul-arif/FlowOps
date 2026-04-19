package com.flowOps.flowOps_service.repository;

import com.flowOps.flowOps_service.entity.designation.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepository extends JpaRepository<Designation,Long> {
}
