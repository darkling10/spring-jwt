package com.example.learnspringjwt.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetails {
    private ResponseStatusEnum responseStatus;

    private String responseMessage;

    public enum ResponseStatusEnum {SUCCESS, ERROR, WARNING, NO_ACCESS}


}
