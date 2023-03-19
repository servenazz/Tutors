package org.example.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.entity.PayStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentServiceDto {
    UserResponseDto user;
    ServiceResponseDto service;
    PayStatus status;
}
