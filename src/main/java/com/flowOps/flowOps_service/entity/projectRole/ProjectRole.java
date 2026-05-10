package com.flowOps.flowOps_service.entity.projectRole;

import jakarta.persistence.*;

@Entity
@Table(name="project_role")
public class ProjectRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    private String name;
}
