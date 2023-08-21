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
import java.util.Optional;

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
        }
    }

    @GetMapping("/{tweetId}")
    public ResponseEntity<?> getTweetById(@PathVariable int tweetId) {
        try {
            Optional<Tweet> tweet = tweetService.getTweetById(tweetId);
            return new ResponseEntity<>(tweet, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel("No Tweet Found With The Specified ID."), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addTweet")
    public ResponseEntity<String> addTweet(@RequestBody SaveTweetRequestDto saveTweetRequestDto){
        try {
            String tweetSaveDescription = tweetService.addTweet(saveTweetRequestDto);
            return new ResponseEntity<>(tweetSaveDescription, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{tweetId}")
    public void deleteTweetById(@PathVariable int tweetId) {
        tweetService.deleteTweetById(tweetId);
    }

}


















