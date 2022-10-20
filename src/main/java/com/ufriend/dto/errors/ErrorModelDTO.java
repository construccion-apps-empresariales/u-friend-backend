package com.ufriend.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor    
@AllArgsConstructor
public class ErrorModelDTO{
    private String field;
    private Object value;
    private String message;
}