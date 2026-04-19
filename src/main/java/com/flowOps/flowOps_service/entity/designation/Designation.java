package com.flowOps.flowOps_service.entity.designation;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="designations")
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="designation_name",nullable = false,length = 50)
    private String designationName;

    @Column(name="level",nullable = false,length=50)
    private Integer level;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

}
