package com.example.employee.entities;

import lombok.Data;

@Data
public class Response {
    private boolean status;
    private String errorMessage;
}
