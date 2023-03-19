package org.example.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.dto.response.StudentServiceDto;
import org.example.security.AuthenticatedUser;
import org.example.service.StudentServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentService")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentServiceController {
    @Autowired
    StudentServiceService studentService;

    @Autowired
    AuthenticatedUser authenticatedUser;

    @Secured({"ROLE_STUDENT"})
    @GetMapping("start-deal")
    public StudentServiceDto startDeal(@RequestParam long serviceId) {
        long studentId = authenticatedUser.get().get().getId();
        return studentService.startDeal(studentId, serviceId);
    }
}

