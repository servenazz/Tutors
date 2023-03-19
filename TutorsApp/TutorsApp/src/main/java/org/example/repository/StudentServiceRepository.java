package org.example.repository;

import org.example.entity.StudentService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentServiceRepository extends JpaRepository<StudentService, Long> {
}
