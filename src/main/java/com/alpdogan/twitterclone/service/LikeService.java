package com.alpdogan.twitterclone.service;

import com.alpdogan.twitterclone.dto.request.SaveLikeRequestDto;
import com.alpdogan.twitterclone.dto.response.LikeResponseDto;
import com.alpdogan.twitterclone.entity.Like;
import com.alpdogan.twitterclone.entity.Tweet;
import com.alpdogan.twitterclone.entity.User;
import com.alpdogan.twitterclone.repository.LikeRepository;
import com.alpdogan.twitterclone.repository.TweetRepository;
import com.alpdogan.twitterclone.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    private LikeRepository likeRepository;

    private TweetRepository tweetRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public LikeService(LikeRepository likeRepository, TweetRepository tweetRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.likeRepository = likeRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Like getLikeById(int likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    // here I can add likes to tweets but not to comments yet. I may add another method for comments or set a logic for deciding wheter it is a tweet or comment to add a like.
    public Like addLikeToTweets(SaveLikeRequestDto saveLikeRequestDto){

        int userIdRequest = saveLikeRequestDto.getUserId();
        int tweetIdRequest = saveLikeRequestDto.getTweetId();

        Tweet tweet = tweetRepository.findById(tweetIdRequest).get();
        User user = userRepository.findById(userIdRequest).get();

        Like like = new Like();

        like.setUser(user);
        like.setTweet(tweet);

        return likeRepository.save(like);
    }

    public void deleteLikeById(int likeId) {
        likeRepository.deleteById(likeId);
    }

}
