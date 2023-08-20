package com.alpdogan.twitterclone.dto.response;

import lombok.Data;

@Data
public class AuthResponse {

    String message;
    int userId;
    String accessToken;
    String refreshToken;

}