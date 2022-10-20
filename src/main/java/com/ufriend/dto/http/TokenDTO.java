package com.ufriend.dto.http;

import lombok.Data;

@Data
public class TokenDTO {
    private String accessToken;
    private String refreshToken;
}
