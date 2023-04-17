package com.example.learnspringjwt.repositories;

import com.example.learnspringjwt.teacher.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectJpaRepository extends JpaRepository<Subjects, String> {
}
