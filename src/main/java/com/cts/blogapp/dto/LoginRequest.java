package com.cts.blogapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid Email Adress")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 3,message = "minimun 3 characters reuired.")
    private String password;




}
