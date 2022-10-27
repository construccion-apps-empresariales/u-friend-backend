package com.ufriend.dto.auth;

import lombok.Data;

@Data
public class UpdatePasswordDTO {
    private Long id;
    private String actualPassword, newPassword, confirmPassword;
}
