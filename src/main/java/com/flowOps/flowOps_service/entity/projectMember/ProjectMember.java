package com.flowOps.flowOps_service.entity.projectMember;

import com.flowOps.flowOps_service.entity.project.Project;
import com.flowOps.flowOps_service.entity.projectRole.ProjectRole;
import com.flowOps.flowOps_service.entity.role.Role;
import com.flowOps.flowOps_service.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="project_members")
@Getter
@Setter
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="project_id",nullable=false)
    private Project project;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;


    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt = LocalDateTime.now();

    @Column(name = "removed_at")
    private LocalDateTime removedAt;

    @ManyToOne
    @JoinColumn(name = "added_by")
    private User addedBy;


}
