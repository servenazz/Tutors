package org.example.service;

import org.example.dto.response.StudentServiceDto;
import org.example.entity.StudentService;

public interface StudentServiceService {
    StudentServiceDto startDeal(long studentId, long serviceId);


}
