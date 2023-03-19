package org.example.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.base.PersistentObject;
import org.example.dto.response.StudentServiceDto;

import javax.persistence.*;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "student_services")
public class StudentService extends PersistentObject {
    @Column(name = "status", nullable = false)
    PayStatus payStatus;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    Service service;
}