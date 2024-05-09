package com.shopg.authservice.service;

import com.shopg.authservice.entity.User;
import com.shopg.authservice.model.AuthRequest;
import com.shopg.authservice.model.AuthResponse;
import com.shopg.authservice.model.RefreshTokenRequest;
import com.shopg.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtService.generateToken(request.getEmail());
        String refreshToken = jwtService.generateRefreshToken(request.getEmail());

        return new AuthResponse(token, refreshToken);
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
