package com.alpdogan.twitterclone.dto.request;

import lombok.Data;

@Data
public class SaveTweetRequestDto {

    int userId;
    String text;

}
