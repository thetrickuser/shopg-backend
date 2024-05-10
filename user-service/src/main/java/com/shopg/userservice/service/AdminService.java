package com.shopg.userservice.service;

import com.shopg.userservice.mapper.UserMapper;
import com.shopg.userservice.model.User;
import com.shopg.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::mapToUserDto).collect(Collectors.toList());
    }
}
