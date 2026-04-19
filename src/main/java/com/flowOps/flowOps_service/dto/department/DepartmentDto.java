package com.flowOps.flowOps_service.dto.department;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class DepartmentDto {
    private Long departmentId;
    @NotBlank(message="Department name is required")
    @Size(max=100,message="Department name must be less than 100 characters")
    private String departmentName;
    private Integer level;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
