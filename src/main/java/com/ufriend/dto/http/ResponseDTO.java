package com.ufriend.dto.http;

import lombok.Data;

@Data
public class ResponseDTO {
    
    private String status;
    private String message = null;
    private Object data = null;

    public ResponseDTO(Boolean status) {
        this.setStatus(status);
    }

    public ResponseDTO(Boolean status, String message, Object data) {
        this.setStatus(status);
        this.message = message;
        this.data = data;
    }

    public void setStatus(Boolean status) {
        this.status = status ? "OK" : "ERROR";
    }

}
