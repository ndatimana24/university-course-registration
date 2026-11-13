package com.university.university_course_registration.repository;

import com.university.university_course_registration.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    Optional<Lecturer> findByEmail(String email);
}