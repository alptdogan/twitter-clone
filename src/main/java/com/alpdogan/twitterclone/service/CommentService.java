package com.alpdogan.twitterclone.service;

import com.alpdogan.twitterclone.dto.request.SaveCommentRequestDto;
import com.alpdogan.twitterclone.dto.response.CommentResponseDto;
import com.alpdogan.twitterclone.entity.Comment;
import com.alpdogan.twitterclone.entity.Tweet;
import com.alpdogan.twitterclone.entity.User;
import com.alpdogan.twitterclone.repository.CommentRepository;
import com.alpdogan.twitterclone.repository.TweetRepository;
import com.alpdogan.twitterclone.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<CommentResponseDto> getAllComments() throws Exception {

        Iterable<Comment> comments = commentRepository.findAll();
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

        if (comments.iterator().hasNext()) {
            for (Comment comment : comments) {
                CommentResponseDto commentResponseDto = modelMapper.map(comment, CommentResponseDto.class);
                commentResponseDtos.add(commentResponseDto);
            }
            return commentResponseDtos;
        }else {
            throw new Exception();
        }

    }

    public Optional<Comment> getCommentById(int commentId) throws Exception {

        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if (commentOptional.isPresent()){
            return commentRepository.findById(commentId);
        }else {
            throw new Exception();
        }

    }

    public String addComment(SaveCommentRequestDto saveCommentRequestDto) throws Exception {

        String textRequest = saveCommentRequestDto.getText();
        int userIdRequest = saveCommentRequestDto.getUserId();
        int tweetIdRequest = saveCommentRequestDto.getTweetId();

        Tweet tweet = tweetRepository.findById(tweetIdRequest).get();
        User user = userRepository.findById(userIdRequest).get();


        Comment comment = new Comment();

        comment.setText(textRequest);
        comment.setUser(user);
        comment.setTweet(tweet);

        if (comment.getText().isBlank()) {
            throw new Exception("Comment Cannot Be Empty.");
        }else if (comment.getText().length() > 140) {
            throw new Exception("Comment Cannot Be Longer Than 140 Characters.");
        }else {
            commentRepository.save(comment);
            return "Your Comment Has Been Successfully Posted.";
        }
    }

    public String deleteCommentById(int commentId) throws Exception {

        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Comment comment = commentOptional.get();

        if (commentOptional.isPresent()) {
            commentRepository.delete(comment);
            return "Comment Has Been Deleted.";
        }else {
            throw new Exception("Cannot Find Any Comment To Delete With The Specified ID.");
        }

    }

}
