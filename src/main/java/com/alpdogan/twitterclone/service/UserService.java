package com.alpdogan.twitterclone.service;

import com.alpdogan.twitterclone.dto.request.SaveUserRequestDto;
import com.alpdogan.twitterclone.dto.request.UpdateUserRequestDto;
import com.alpdogan.twitterclone.entity.User;
import com.alpdogan.twitterclone.repository.UserRepository;
import com.alpdogan.twitterclone.dto.response.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> getAllUsers() {

        Iterable<User> users = userRepository.findAll();

        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        if (users.iterator().hasNext()) {

            for (User user : users) {
                UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
                userResponseDtos.add(userResponseDto);
            }

        }

        return userResponseDtos;

    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User addUser(SaveUserRequestDto saveUserRequestDto) {

        User user = modelMapper.map(saveUserRequestDto, User.class);

        return userRepository.save(user);

    }

    public String updateUserById(UpdateUserRequestDto updateUserRequestDto) {

        int idUserRequest = updateUserRequestDto.getId();
        String nameUserRequest = updateUserRequestDto.getUsername();

        Optional<User> userOptional = userRepository.findById(idUserRequest);

        if (userOptional.isPresent()) {

            User user = userOptional.get();
            user.setUsername(nameUserRequest);

            userRepository.save(user);
        }

        return "User has been updated successfully.";

    }

    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

}
