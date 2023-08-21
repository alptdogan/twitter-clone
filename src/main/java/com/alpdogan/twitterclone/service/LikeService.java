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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<LikeResponseDto> getAllLikes() throws Exception {

        Iterable<Like> likes = likeRepository.findAll();
        List<LikeResponseDto> likeResponseDtos = new ArrayList<>();

        if (likes.iterator().hasNext()) {
            for (Like like : likes) {
                LikeResponseDto likeResponseDto = modelMapper.map(like, LikeResponseDto.class);
                likeResponseDtos.add(likeResponseDto);
            }
            return likeResponseDtos;
        }else {
            throw new Exception();
        }

    }

    public Optional<Like> getLikeById(int likeId) throws Exception {

        Optional<Like> likeOptional = likeRepository.findById(likeId);

        if (likeOptional.isPresent()){
            return likeRepository.findById(likeId);
        }else {
            throw new Exception();
        }

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

    public String deleteLikeById(int likeId) throws Exception {

        Optional<Like> likeOptional = likeRepository.findById(likeId);
        Like like = likeOptional.get();

        if (likeOptional.isPresent()) {
            likeRepository.delete(like);
            return "Your Like Has Been Deleted.";
        }else {
            throw new Exception("Cannot Find Any Likes To Delete With The Specified ID.");
        }
    }

}
