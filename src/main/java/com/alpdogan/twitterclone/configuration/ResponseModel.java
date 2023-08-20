package com.alpdogan.twitterclone.configuration;

import lombok.Data;

@Data
public class ResponseModel {

    String message;

    public ResponseModel(String message) {
        this.message = message;
    }

}
