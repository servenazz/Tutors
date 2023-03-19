package org.example.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.dto.request.UserRequestDto;
import org.example.dto.response.ServiceResponseDto;
import org.example.service.ServiceService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServiceController {

    private final ServiceService serviceService;
    @Secured({"ADMIN", "TUTOR"})
    @PostMapping
    public void create(@RequestBody UserRequestDto request) {
        serviceService.create(request);
    }

    @Secured({"ADMIN", "TUTOR"})
    @PutMapping
    public void update(@RequestBody UserRequestDto request) {
        serviceService.update(request);
    }

    @Secured({"ADMIN", "TUTOR"})
    @DeleteMapping
    public void delete(@RequestParam long serviceId) {
        serviceService.delete(serviceId);
    }

    @Secured({"ADMIN", "TUTOR"})
    @GetMapping
    public ServiceResponseDto get(@RequestParam long serviceId) {
        return serviceService.get(serviceId);
    }

//    @Secured({"ADMIN", "TUTOR", "STUDENT"})
    @GetMapping("all")
    public List<ServiceResponseDto> getAll(){
        return serviceService.getAll();
    }

    @GetMapping("/by-subject")
    public List<ServiceResponseDto> getServices(@RequestParam long subjectId) {
        return serviceService.getServices(subjectId);
    }
}
