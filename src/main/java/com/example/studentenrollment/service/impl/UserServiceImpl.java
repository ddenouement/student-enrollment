package com.example.studentenrollment.service.impl;

import com.example.studentenrollment.model.User;
import com.example.studentenrollment.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUser = userRepository.findByEmail(username);
        if(foundUser.isEmpty()){
            throw new UsernameNotFoundException("Could not find user with email: " + username);
        }
        return foundUser.get();
    }
}
