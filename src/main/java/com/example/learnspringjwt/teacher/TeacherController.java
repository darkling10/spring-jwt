package com.example.learnspringjwt.teacher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/teacher/")
public class TeacherController {

    @GetMapping("/hi")
    public ResponseEntity<Object> teacherHi() {
        return ResponseEntity.accepted().body("Hello");
    }
}
