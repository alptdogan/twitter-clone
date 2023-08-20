package com.alpdogan.twitterclone.controller;

import com.alpdogan.twitterclone.dto.request.SaveUserRequestDto;
import com.alpdogan.twitterclone.dto.request.UpdateUserRequestDto;
import com.alpdogan.twitterclone.dto.response.UserResponseDto;
import com.alpdogan.twitterclone.entity.User;
import com.alpdogan.twitterclone.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("updateUser")
    public String updateUserById(@RequestBody UpdateUserRequestDto updateUserRequestDto) {
        return userService.updateUserById(updateUserRequestDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable int userId) {
        userService.deleteUserById(userId);
    }

}
