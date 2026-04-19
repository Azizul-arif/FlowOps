package com.flowOps.flowOps_service.dto.designation;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DesignationDto {

    private Long designation_id;
    @NotBlank(message = "Designation name is required")
    @Size(max = 50,message = "Designation Name cant be more than 50 character")
    private String designationName;
    private Integer level;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
