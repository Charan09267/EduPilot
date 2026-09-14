package net.edupilot.mockinterviewservice.repository;

import net.edupilot.mockinterviewservice.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface InterviewRepository extends JpaRepository<Interview, Integer> {
    Optional<Interview> findById(long id);
}
