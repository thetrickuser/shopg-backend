package com.shopg.userservice.service;

import com.shopg.userservice.entity.User;
import com.shopg.userservice.mapper.SignupRequestMapper;
import com.shopg.userservice.model.SignupRequest;
import com.shopg.userservice.model.SignupResponse;
import com.shopg.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    SignupRequestMapper signupRequestMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public SignupResponse signup(SignupRequest request) {
        try {
            User user = signupRequestMapper.mapRequestToUser(request);
            passwordEncoder.encode(user.getPassword());
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new SignupResponse("User added successfully");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.contains("@")) {
            return userRepository.findByEmail(username);
        } else if (username.length() == 10) {
            return userRepository.findByPhone(username);
        }
        throw new UsernameNotFoundException("No user found");
    }
}
