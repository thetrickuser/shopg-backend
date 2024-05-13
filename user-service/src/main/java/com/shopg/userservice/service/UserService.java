package com.shopg.userservice.service;

import com.shopg.userservice.entity.User;
import com.shopg.userservice.mapper.SignupRequestMapper;
import com.shopg.userservice.mapper.UserMapper;
import com.shopg.userservice.model.*;
import com.shopg.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private SignupRequestMapper signupRequestMapper;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMapper userMapper;

    public SignupResponse signup(SignupRequest request) {
        try {
            User user = signupRequestMapper.mapRequestToUser(request);
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new SignupResponse("User added successfully");
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String refreshToken = jwtService.generateRefreshToken(request.getEmail());
        UserDto user = userMapper.mapToUserDto(userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("No user found")));

        return new LoginResponse(refreshToken, user);
    }

    public String refresh(RefreshTokenRequest request) {
        String username = jwtService.extractUsername(request.getToken());
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No user found"));
        if (jwtService.isTokenValid(request.getToken(), user)) {
            return jwtService.generateToken(username);
        } else {
            throw new BadCredentialsException("Invalid refresh token. Please login again");
        }
    }
}
