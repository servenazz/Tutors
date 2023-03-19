package org.example.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.entity.StatusType;
import org.example.entity.Subject;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceResponseDto {
    String name;
    SubjectResponseDto subject;
    UserResponseDto user;
    String experience;
    StatusType status;
    long price;
    double rating;
}