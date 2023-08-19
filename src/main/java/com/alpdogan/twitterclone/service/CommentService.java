package com.alpdogan.twitterclone.service;

import com.alpdogan.twitterclone.dto.request.SaveCommentRequestDto;
import com.alpdogan.twitterclone.entity.Comment;
import com.alpdogan.twitterclone.entity.Tweet;
import com.alpdogan.twitterclone.entity.User;
import com.alpdogan.twitterclone.repository.CommentRepository;
import com.alpdogan.twitterclone.repository.TweetRepository;
import com.alpdogan.twitterclone.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    private TweetRepository tweetRepository;

    private UserRepository userRepository;


    private ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, TweetRepository tweetRepository, UserRepository userRepository, ModelMapper modelMapper) {

        this.commentRepository = commentRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }

    public Comment addComment(SaveCommentRequestDto saveCommentRequestDto){

        String textRequest = saveCommentRequestDto.getText();
        int userIdRequest = saveCommentRequestDto.getUserId();
        int tweetIdRequest = saveCommentRequestDto.getTweetId();

        Tweet tweet = tweetRepository.findById(tweetIdRequest).get();
        User user = userRepository.findById(userIdRequest).get();


        Comment comment = new Comment();

        comment.setText(textRequest);
        comment.setUser(user);
        comment.setTweet(tweet);

        return commentRepository.save(comment);

    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable int commentId) {
        commentRepository.deleteById(commentId);
    }

}