package org.example.controller;

import org.example.dto.request.SubjectRequestDto;
import org.example.dto.response.SubjectResponseDto;
import org.example.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @Secured({"ADMIN", "TUTOR"})
    @PostMapping
    public void create(@RequestBody SubjectRequestDto request) {
        subjectService.create(request);
    }
    @Secured({"ADMIN", "TUTOR"})
    @PutMapping
    public void update(@RequestBody SubjectRequestDto request) {
        subjectService.update(request);
    }
    @Secured({"ADMIN", "TUTOR"})
    @DeleteMapping
    public void delete(@RequestParam long subjectId) {
        subjectService.delete(subjectId);
    }
    @Secured({"ADMIN", "TUTOR", "STUDENT"})
    @GetMapping
    public SubjectResponseDto get(@RequestParam long subjectId) {
        return subjectService.get(subjectId);
    }

//    @Secured({"ADMIN", "TUTOR", "STUDENT"})
    @GetMapping("all")
    public List<SubjectResponseDto> getAll(){
        return subjectService.getAll();
    }
}