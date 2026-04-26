package com.cts.blogapp.controller;

import com.cts.blogapp.dto.LoginRequest;
import com.cts.blogapp.dto.TokenResponse;
import com.cts.blogapp.dto.UserDto;
import com.cts.blogapp.entity.User;
import com.cts.blogapp.repositories.UserRepository;
import com.cts.blogapp.security.JwtService;
import com.cts.blogapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody LoginRequest loginRequest){
       try {
           UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
           authenticationManager.authenticate(authentication);
           User user = userRepository.findByEmail(loginRequest.getEmail()).get();
           var tokenResponse = TokenResponse.builder()
                   .accessToekn(jwtService.generateAccessToken(user))
                   .refreshToekn(jwtService.generateRefreshToken(user))
                   .userDto(modelMapper.map(user,UserDto.class))
                   .build();

            return new ResponseEntity<>(tokenResponse,HttpStatus.CREATED);
       } catch (AuthenticationException e){
           throw new BadCredentialsException("Invalid UserName or Password");
       }

    }

}
