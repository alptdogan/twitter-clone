package com.alpdogan.twitterclone.controller;

import com.alpdogan.twitterclone.configuration.ResponseModel;
import com.alpdogan.twitterclone.dto.request.SaveUserRequestDto;
import com.alpdogan.twitterclone.dto.request.UpdateUserRequestDto;
import com.alpdogan.twitterclone.dto.response.UserResponseDto;
import com.alpdogan.twitterclone.entity.User;
import com.alpdogan.twitterclone.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dsig.keyinfo.X509IssuerSerial;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        try {
            List<UserResponseDto> userResponseDtos = userService.getAllUsers();
            return new ResponseEntity<>(userResponseDtos, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel("There Is No User To Be Listed."), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        try {
            User user = userService.getUserById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModel("No User Found With The Specified ID."), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            String userSaveDescription = userService.addUser(user);
            return new ResponseEntity<>(userSaveDescription, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequestDto updateUserRequestDto) {
        try {
            String updateUserDescription = userService.updateUser(updateUserRequestDto);
            return new ResponseEntity<>(updateUserDescription, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable int userId) {
        try{
            String deleteUserDescription = userService.deleteUserById(userId);
            return new ResponseEntity<>(deleteUserDescription, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
