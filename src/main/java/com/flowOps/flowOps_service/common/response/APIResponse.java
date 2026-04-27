package com.flowOps.flowOps_service.common.response;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse <T>{
    private int statusCode;
    private String status;
    private String message;
    private T data;
    private Object errors;
    private LocalDateTime timestamp;
}
