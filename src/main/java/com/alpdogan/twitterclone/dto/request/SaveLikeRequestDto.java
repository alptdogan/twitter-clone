package com.alpdogan.twitterclone.dto.request;

import lombok.Data;

@Data
public class SaveLikeRequestDto {

    int userId;
    int tweetId;

}
