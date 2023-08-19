package com.alpdogan.twitterclone.service;

import com.alpdogan.twitterclone.dto.request.SaveTweetRequestDto;
import com.alpdogan.twitterclone.dto.response.TweetResponseDto;
import com.alpdogan.twitterclone.entity.Tweet;
import com.alpdogan.twitterclone.repository.TweetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TweetService {

    private TweetRepository tweetRepository;

    private ModelMapper modelMapper;

    public TweetService(TweetRepository tweetRepository, ModelMapper modelMapper) {
        this.tweetRepository = tweetRepository;
        this.modelMapper = modelMapper;
    }

    public List<TweetResponseDto> getAllTweets() {

        Iterable<Tweet> tweets = tweetRepository.findAll();
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();

        if (tweets.iterator().hasNext()) {
            for (Tweet tweet : tweets) {
                TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
                tweetResponseDtos.add(tweetResponseDto);
            }
        }

        return tweetResponseDtos;

    }

    public Tweet getTweetById(int tweetId) {
        return tweetRepository.findById(tweetId).orElse(null);
    }

    public Tweet addTweet(SaveTweetRequestDto saveTweetRequestDto) {

        Tweet tweet = modelMapper.map(saveTweetRequestDto, Tweet.class);

        return tweetRepository.save(tweet);

    }

    public void deleteTweetById(int tweetId){
        tweetRepository.deleteById(tweetId);
    }

}
