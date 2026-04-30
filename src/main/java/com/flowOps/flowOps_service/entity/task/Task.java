package com.flowOps.flowOps_service.entity.task;

import com.flowOps.flowOps_service.common.enums.TaskPriority;
import com.flowOps.flowOps_service.common.enums.TaskStatus;
import com.flowOps.flowOps_service.entity.project.Project;
import com.flowOps.flowOps_service.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="tasks")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @Column(nullable = false,name="title")
    private String title;

    @Column(nullable = false,name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;


    private LocalDate dueDate;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
