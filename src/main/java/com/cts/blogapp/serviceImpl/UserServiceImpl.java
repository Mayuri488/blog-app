package com.cts.blogapp.serviceImpl;

import com.cts.blogapp.dto.UserDto;
import com.cts.blogapp.entity.Role;
import com.cts.blogapp.entity.User;
import com.cts.blogapp.exceptiion.ResourceNotFoundException;
import com.cts.blogapp.repositories.UserRepository;
import com.cts.blogapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    @Autowired
    private  final UserRepository userRepository;
    @Autowired
    private  final PasswordEncoder passwordEncoder;
    @Autowired
    private  final ModelMapper modelMapper;


    @Override
    public UserDto registerUser(UserDto userDto) {
        //
        User user= modelMapper.map(userDto,User.class);
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_GUEST);
        user.setEnabled(true);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto delete(Long userId) {
        return null;
    }

    private void validateUser(User user) {
        User user1 = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (user1 != null) {
            throw new ResourceNotFoundException("User with email " + user.getEmail() + " already exists");
        }
    }
}