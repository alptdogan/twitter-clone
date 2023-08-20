package com.alpdogan.twitterclone.dto.request;

import lombok.Data;

@Data
public class RefreshRequest {

    int userId;
    String refreshToken;
}