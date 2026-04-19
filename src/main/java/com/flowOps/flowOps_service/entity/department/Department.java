package com.flowOps.flowOps_service.entity.department;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@Table(name="departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="department_name",nullable = false,length=100)
    private String departmentName;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
