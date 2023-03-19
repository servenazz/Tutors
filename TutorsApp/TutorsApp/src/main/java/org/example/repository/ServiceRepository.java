package org.example.repository;
import org.example.entity.Service;
import org.example.entity.Subject;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    Optional<Service> findByUser(User user);

    List<Service> findBySubject(Subject subject);
}
