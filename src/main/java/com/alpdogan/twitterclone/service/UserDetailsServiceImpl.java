package com.alpdogan.twitterclone.service;

import com.alpdogan.twitterclone.entity.User;
import com.alpdogan.twitterclone.repository.UserRepository;
import com.alpdogan.twitterclone.jwt.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(int id) {
        User user = userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }

}