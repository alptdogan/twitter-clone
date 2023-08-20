package com.alpdogan.twitterclone.dto.request;

import lombok.Data;

@Data
public class SaveUserRequestDto {

    String username;
    String password;

    int id;

    public int getId() {
        return id;
    }

}
