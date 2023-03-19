package org.example.service;

import org.example.dto.request.UserRequestDto;
import org.example.dto.response.ServiceResponseDto;
import org.example.dto.response.SubjectResponseDto;

import java.util.List;

public interface ServiceService {
    List<ServiceResponseDto> getAll();

    void create(UserRequestDto request);

    void update(UserRequestDto request);

    void delete(long userId);

    ServiceResponseDto get(long serviceId);
    List<ServiceResponseDto> getServices(long subjectId);
}
