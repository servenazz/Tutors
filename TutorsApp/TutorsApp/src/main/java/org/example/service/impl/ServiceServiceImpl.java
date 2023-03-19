package org.example.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.dto.request.UserRequestDto;
import org.example.dto.response.ServiceResponseDto;
import org.example.dto.response.SubjectResponseDto;
import org.example.dto.response.UserResponseDto;
import org.example.entity.Service;
import org.example.entity.StatusType;
import org.example.entity.Subject;
import org.example.exception.BaseRuntimeException;
import org.example.repository.ServiceRepository;
import org.example.repository.SubjectRepository;
import org.example.service.ServiceService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    ServiceRepository serviceRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public List<ServiceResponseDto> getAll() {

        return List.of();
    }

    @Override
    public void create(UserRequestDto request) {

    }

    @Override
    public void update(UserRequestDto request) {

    }

    @Override
    public void delete(long userId) {

    }

    @Override
    public ServiceResponseDto get(long serviceId) {
        return null;
    }

    @Override
    public List<ServiceResponseDto> getServices(long subjectId) {
        //check is subject present in db
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isEmpty()) {

            throw new BaseRuntimeException("Указанного предмета не существует");
        }
        //find all services in db by subject
        List<Service> services = serviceRepository.findBySubject(subject.get());

        //transform service entity to dto

        /****************************METHOD SIMPLE OLD JAVA STYLE
         //create list of service dtos
         List<ServiceResponseDto> servicesDto = new ArrayList<>();
         //do transform operation for each service from services by subject in db
         for (Service s :
         services) {
         //Create dto based on entity
         ServiceResponseDto dto = new ServiceResponseDto(
         s.getName(),
         new SubjectResponseDto(subject.get().getTitle(), subject.get().getDescription()),
         "5 лет",
         StatusType.FREE,
         5000,
         4.5
         );
         //add dto to list of dtos
         servicesDto.add(dto);
         }
         ********************************************************/

        /********************USING STREAM API*******************/
        return services.stream()
                .map(s -> new ServiceResponseDto(
                        s.getName(),
                        new SubjectResponseDto(subject.get().getId(), subject.get().getTitle(), subject.get().getDescription()),
                        new UserResponseDto(s.getUser()),
                        s.getUser().getExperience(),
                        s.getUser().getStatus(),
                        s.getPrice(),
                        s.getUser().getRating()
                )).collect(Collectors.toList());
    }
}
