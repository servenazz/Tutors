package org.example.service;

import org.example.dto.request.AuthRequestDto;
import org.example.dto.request.UserRequestDto;
import org.example.dto.response.AuthResponseDto;
import org.example.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAll();

    void create(UserRequestDto request);

    UserResponseDto get(long userId);

    void update(UserRequestDto request);

    void delete(long userId);

    AuthResponseDto login(AuthRequestDto request);
}
