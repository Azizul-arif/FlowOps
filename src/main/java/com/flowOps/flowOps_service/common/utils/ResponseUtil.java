package com.flowOps.flowOps_service.common.utils;

import com.flowOps.flowOps_service.common.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseUtil {
    public static <T> ResponseEntity<APIResponse<T>> success (T data,String message)
    {
        APIResponse<T> response=APIResponse.<T>builder()
                .statusCode(HttpStatus.OK.value())
                .status("Success")
                .message(message)
                .data(data)
                .errors(null)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    public static <T> ResponseEntity<APIResponse<T>> created(T data, String message) {
        APIResponse<T> response = APIResponse.<T>builder()
                .statusCode(HttpStatus.CREATED.value())
                .status("SUCCESS")
                .message(message)
                .data(data)
                .errors(null)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
