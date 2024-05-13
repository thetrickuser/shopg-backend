package com.shopg.userservice.controller;

import com.shopg.userservice.mapper.UserMapper;
import com.shopg.userservice.model.UserDto;
import com.shopg.userservice.repository.UserRepository;
import com.shopg.userservice.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUser")
    public UserDto getUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        Optional<com.shopg.userservice.entity.User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            return userMapper.mapToUserDto(userOptional.get());
        }
        return null;
    }
}
