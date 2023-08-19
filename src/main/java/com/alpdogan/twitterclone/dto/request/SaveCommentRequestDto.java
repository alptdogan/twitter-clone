package com.alpdogan.twitterclone.dto.request;

import lombok.Data;

@Data
public class SaveCommentRequestDto {

    String text;
    int userId;
    int tweetId;

}
