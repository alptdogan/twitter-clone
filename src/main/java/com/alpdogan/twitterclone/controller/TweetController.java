package com.alpdogan.twitterclone.controller;

import com.alpdogan.twitterclone.configuration.ResponseModel;
import com.alpdogan.twitterclone.dto.request.SaveTweetRequestDto;
import com.alpdogan.twitterclone.dto.response.TweetResponseDto;
import com.alpdogan.twitterclone.entity.Tweet;
import com.alpdogan.twitterclone.service.TweetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    private TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTweets() {
        try {

            List<TweetResponseDto> tweetResponseDtos = tweetService.getAllTweets();

            return new ResponseEntity<>(tweetResponseDtos, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity<>(new ResponseModel("There Is No Tweets To Be Listed."), HttpStatus.NOT_FOUND);

        }    }

    @GetMapping("/{tweetId}")
    public Tweet getTweetById(@PathVariable int tweetId) {
        return tweetService.getTweetById(tweetId);
    }

    @PostMapping("/addTweet")
    public Tweet addTweet(@RequestBody SaveTweetRequestDto saveTweetRequestDto){
        return tweetService.addTweet(saveTweetRequestDto);
    }

    @DeleteMapping("/{tweetId}")
    public void deleteTweetById(@PathVariable int tweetId) {
        tweetService.deleteTweetById(tweetId);
    }

}


















