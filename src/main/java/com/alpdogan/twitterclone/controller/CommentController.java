package com.alpdogan.twitterclone.controller;

import com.alpdogan.twitterclone.dto.request.SaveCommentRequestDto;
import com.alpdogan.twitterclone.entity.Comment;
import com.alpdogan.twitterclone.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    public Comment addComment(@RequestBody SaveCommentRequestDto saveCommentRequestDto) {
        return commentService.addComment(saveCommentRequestDto);
    }

    @DeleteMapping("/deleteCommentById")
    public void deleteCommentById(@PathVariable int commentId) {
        commentService.deleteCommentById(commentId);
    }

}
