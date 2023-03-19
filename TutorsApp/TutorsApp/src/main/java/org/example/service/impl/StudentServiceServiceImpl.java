package org.example.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.dto.response.ServiceResponseDto;
import org.example.dto.response.StudentServiceDto;
import org.example.dto.response.SubjectResponseDto;
import org.example.dto.response.UserResponseDto;
import org.example.entity.*;
import org.example.exception.BaseRuntimeException;
import org.example.repository.ServiceRepository;
import org.example.repository.StudentServiceRepository;
import org.example.repository.UserRepository;
import org.example.service.StudentServiceService;

import java.util.Date;
import java.util.Optional;

@org.springframework.stereotype.Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StudentServiceServiceImpl implements StudentServiceService {

    UserRepository userRepository;
    ServiceRepository serviceRepository;
    StudentServiceRepository studentServiceRepository;

    @Override
    public StudentServiceDto startDeal(long studentId, long serviceId) {
        Optional<User> student = userRepository.findById(studentId);
        if(student.isEmpty()){
            throw new BaseRuntimeException("Указаный студент не найден.");
        }
        if(!student.get().getRole().equals(UserRoleType.STUDENT)) {
            throw new BaseRuntimeException("Указаный пользователь не студент.");
        }
        Optional<Service> service = serviceRepository.findById(serviceId);
        if(service.isEmpty()){
            throw new BaseRuntimeException("Указанная услуга не найдена.");
        }
        if(service.get().getUser().getStatus().equals(StatusType.BUSY)) {
            throw new BaseRuntimeException("Преподаватель занят, выберите другую услугу.");
        }
        StudentService studentService = new StudentService();
        studentService.setUser(student.get());
        studentService.setService(service.get());
        studentService.setPayStatus(PayStatus.NOT_PAYED);
        studentService.setCreatedAt(new Date());
        studentServiceRepository.save(studentService);

        StudentServiceDto studentServiceDto = new StudentServiceDto();
        studentServiceDto.setUser(new UserResponseDto(
                studentId,
                student.get().getLogin(),
                student.get().getFirstName(),
                student.get().getLastName(),
                student.get().getRole()
        ));
        studentServiceDto.setService(new ServiceResponseDto(
                service.get().getName(),
                new SubjectResponseDto(service.get().getSubject().getId(),service.get().getSubject().getTitle(),service.get().getSubject().getDescription()),
                new UserResponseDto(service.get().getUser()),
                service.get().getUser().getExperience(),
                service.get().getUser().getStatus(),
                service.get().getPrice(),
                service.get().getUser().getRating()
        ));
        studentServiceDto.setStatus(studentService.getPayStatus());

        return studentServiceDto;
    }
}
