package com.alpdogan.twitterclone.service;

import com.alpdogan.twitterclone.dto.request.SaveTweetRequestDto;
import com.alpdogan.twitterclone.dto.response.TweetResponseDto;
import com.alpdogan.twitterclone.entity.Tweet;
import com.alpdogan.twitterclone.entity.User;
import com.alpdogan.twitterclone.repository.TweetRepository;
import com.alpdogan.twitterclone.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TweetService {

    private TweetRepository tweetRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public TweetService(TweetRepository tweetRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<TweetResponseDto> getAllTweets() throws Exception {

        Iterable<Tweet> tweets = tweetRepository.findAll();
        List<TweetResponseDto> tweetResponseDtos = new ArrayList<>();

        if (tweets.iterator().hasNext()) {
            for (Tweet tweet : tweets) {
                TweetResponseDto tweetResponseDto = modelMapper.map(tweet, TweetResponseDto.class);
                tweetResponseDtos.add(tweetResponseDto);
            }
            return tweetResponseDtos;
        }else{
            throw new Exception();
        }

    }

    public Optional<Tweet> getTweetById(int tweetId) throws Exception {

        Optional<Tweet> tweetOptional = tweetRepository.findById(tweetId);

        if (tweetOptional.isPresent()){
            return tweetRepository.findById(tweetId);
        }else {
            throw new Exception();
        }

    }

    public String addTweet(SaveTweetRequestDto saveTweetRequestDto) throws Exception {

        String textRequest = saveTweetRequestDto.getText();
        int userIdRequest = saveTweetRequestDto.getUserId();

        User user = userRepository.findById(userIdRequest).get();

        Tweet tweet = new Tweet();

        tweet.setText(textRequest);
        tweet.setUser(user);

        if (tweet.getText().isBlank()) {
            throw new Exception("Tweet Text Cannot Be Empty.");
        }else if (tweet.getText().length() > 140) {
            throw new Exception("Tweet Text Cannot Be Longer Than 140 Characters.");
        }else {
            tweetRepository.save(tweet);
            return "Your Tweet Has Been Successfully Posted.";
        }

    }

    public void deleteTweetById(int tweetId){
        tweetRepository.deleteById(tweetId);
    }

}
