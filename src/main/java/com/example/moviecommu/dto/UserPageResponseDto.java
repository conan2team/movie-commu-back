package com.example.moviecommu.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserPageResponseDto {
    private Long userCnt;
    private List<UserDto> users;
}