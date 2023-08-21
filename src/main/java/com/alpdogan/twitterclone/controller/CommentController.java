package com.alpdogan.twitterclone.controller;

import com.alpdogan.twitterclone.configuration.ResponseModel;
import com.alpdogan.twitterclone.dto.request.SaveCommentRequestDto;
import com.alpdogan.twitterclone.dto.response.CommentResponseDto;
import com.alpdogan.twitterclone.entity.Comment;
import com.alpdogan.twitterclone.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<?> getAllComments(){
        try {
            List<CommentResponseDto> commentResponseDtos = commentService.getAllComments();
            return new ResponseEntity<>(commentResponseDtos, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel("No Comment Found With The Specified ID."), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?>  getCommentById(@PathVariable int commentId) {
        try {
            Optional<Comment> comment = commentService.getCommentById(commentId);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel("No Comment Found With The Specified ID."), HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping("/addComment")
    public ResponseEntity<String> addComment(@RequestBody SaveCommentRequestDto saveCommentRequestDto) {
        try {
            String commentSaveDescription = commentService.addComment(saveCommentRequestDto);
            return new ResponseEntity<>(commentSaveDescription, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable int commentId) {
        try {
            String deleteCommentDescription = commentService.deleteCommentById(commentId);
            return new ResponseEntity<>(deleteCommentDescription, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
