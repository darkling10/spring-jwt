package com.example.learnspringjwt.repositories;

import com.example.learnspringjwt.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherJpaRepository extends JpaRepository<Teacher, String> {
    //    @Query("SELECT td.email, td.firstName, td.subjects FROM  teacher_details td WHERE td.email = ?1")
    Teacher findOneByEmail(String email);

}
