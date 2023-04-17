package com.example.learnspringjwt.customResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomResponse<T> {
    private String status;
    private String message;
    private T data;
}
