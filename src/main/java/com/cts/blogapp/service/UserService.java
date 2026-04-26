package com.cts.blogapp.service;

import com.cts.blogapp.dto.UserDto;


public interface UserService {

    UserDto registerUser(UserDto userDto);

    UserDto delete(Long userId);
}