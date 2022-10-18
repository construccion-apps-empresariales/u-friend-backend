package com.ufriend.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    
    private Boolean status;
    private String message = null;
    private Object data = null;
    
    public ResponseDTO(Boolean status) {
        this.status = status;
    }

    public ResponseDTO(Boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
