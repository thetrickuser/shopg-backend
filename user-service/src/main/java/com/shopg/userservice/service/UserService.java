package com.shopg.userservice.service;

import com.shopg.userservice.entity.User;
import com.shopg.userservice.mapper.SignupRequestMapper;
import com.shopg.userservice.model.LoginRequest;
import com.shopg.userservice.model.LoginResponse;
import com.shopg.userservice.model.SignupRequest;
import com.shopg.userservice.model.SignupResponse;
import com.shopg.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        String token = jwtService.generateToken(request.getEmail());
        String refreshToken = jwtService.generateRefreshToken(request.getEmail());

        return new LoginResponse(token, refreshToken);
    }
}
