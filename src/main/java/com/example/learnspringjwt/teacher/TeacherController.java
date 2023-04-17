package com.example.learnspringjwt.teacher;

import com.example.learnspringjwt.repositories.TeacherJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teacher/")
public class TeacherController {

    private TeacherDAOService teacherDAOService;
    private TeacherJpaRepository teacherJpaRepository;

    @GetMapping("/hi")
    public ResponseEntity<Object> teacherHi() {
        return ResponseEntity.accepted().body("Hello");
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllTeachers(@RequestParam String email) {
        List<Teacher> teacher = teacherJpaRepository.findAll();

        return ResponseEntity.accepted().body(teacher);
    }
}
