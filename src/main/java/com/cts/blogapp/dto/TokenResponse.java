package com.cts.blogapp.dto;

import lombok.*;

@Data
@Builder
public class TokenResponse {

    private String accessToekn;
    private String refreshToekn;
    private UserDto userDto;

}
