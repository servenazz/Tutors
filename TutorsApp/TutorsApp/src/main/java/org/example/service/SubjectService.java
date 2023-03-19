package org.example.service;

import org.example.dto.request.SubjectRequestDto;
import org.example.dto.response.SubjectResponseDto;

import java.util.List;

public interface SubjectService {
    void create(SubjectRequestDto request);

    void update(SubjectRequestDto request);

    void delete(long subjectId);

    SubjectResponseDto get(long subjectId);

    List<SubjectResponseDto> getAll();
}
