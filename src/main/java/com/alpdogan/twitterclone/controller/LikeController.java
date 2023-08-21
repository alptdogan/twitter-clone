package com.alpdogan.twitterclone.controller;

import com.alpdogan.twitterclone.configuration.ResponseModel;
import com.alpdogan.twitterclone.dto.request.SaveLikeRequestDto;
import com.alpdogan.twitterclone.dto.response.LikeResponseDto;
import com.alpdogan.twitterclone.entity.Like;
import com.alpdogan.twitterclone.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllLikes(){
        try {
            List<LikeResponseDto> likeResponseDtos = likeService.getAllLikes();
            return new ResponseEntity<>(likeResponseDtos, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel("No Likes Found To Be Listed"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{likeId}")
    public ResponseEntity<?> getLikeById(@PathVariable int likeId) {
        try {
            Optional<Like> like = likeService.getLikeById(likeId);
            return new ResponseEntity<>(like, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel("No Likes Found With The Specified ID."), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addLikeToTweets")
    public ResponseEntity<?> addLikeToTweets(@RequestBody SaveLikeRequestDto saveLikeRequestDto) {
        try {
            Like likeSaveDescription = likeService.addLikeToTweets(saveLikeRequestDto);
            return new ResponseEntity<>(likeSaveDescription, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<String> deleteLikeById(@PathVariable int likeId) {
        try {
            String deleteLikeDescription = likeService.deleteLikeById(likeId);
            return new ResponseEntity<>(deleteLikeDescription, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
