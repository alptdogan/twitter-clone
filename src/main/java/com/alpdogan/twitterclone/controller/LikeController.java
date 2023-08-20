package com.alpdogan.twitterclone.controller;

import com.alpdogan.twitterclone.dto.request.SaveLikeRequestDto;
import com.alpdogan.twitterclone.entity.Like;
import com.alpdogan.twitterclone.service.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/{likeId}")
    public Like getLikeById(@PathVariable int likeId) {
        return likeService.getLikeById(likeId);
    }

    @PostMapping("/addLikeToTweets")
    public Like addLikeToTweets(@RequestBody SaveLikeRequestDto saveLikeRequestDto) {
        return likeService.addLikeToTweets(saveLikeRequestDto);
    }

    @DeleteMapping("/deleteLikeById")
    public void deleteLikeById(@PathVariable int likeId) {
        likeService.deleteLikeById(likeId);
    }

}
