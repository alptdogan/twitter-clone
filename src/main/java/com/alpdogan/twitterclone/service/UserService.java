package com.alpdogan.twitterclone.service;

import com.alpdogan.twitterclone.dto.request.UpdateUserRequestDto;
import com.alpdogan.twitterclone.entity.User;
import com.alpdogan.twitterclone.repository.UserRepository;
import com.alpdogan.twitterclone.dto.response.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserResponseDto> getAllUsers() throws Exception {

        Iterable<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        if (users.iterator().hasNext()) {
            for (User user : users) {
                UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
                userResponseDtos.add(userResponseDto);
            }
            return userResponseDtos;
        }else {
            throw new Exception();
        }

    }

    public User getUserById(int userId) throws Exception {

        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isPresent()) {
            return userRepository.findById(userId).get();
        }else {
            throw new Exception();
        }
    }

    public String addUser(User user) throws Exception {

        if(user.getUsername().isBlank()) {
            throw new Exception("Username Cannot Be Empty.");
        }else {
            userRepository.save(user);
            return user.getUsername() + " Has Been Created Successfully.";
        }

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

    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

}
