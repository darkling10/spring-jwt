package com.example.learnspringjwt.repositories;

import com.example.learnspringjwt.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherJpaRepository extends JpaRepository<Teacher, String> {
}
