package com.alpdogan.twitterclone.controller;

import com.alpdogan.twitterclone.dto.request.SaveTweetRequestDto;
import com.alpdogan.twitterclone.dto.response.TweetResponseDto;
import com.alpdogan.twitterclone.entity.Tweet;
import com.alpdogan.twitterclone.service.TweetService;
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
    public List<TweetResponseDto> getAllTweets() {
        return tweetService.getAllTweets();
    }

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
