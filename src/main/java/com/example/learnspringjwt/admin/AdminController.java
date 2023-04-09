package com.example.learnspringjwt.admin;

import com.example.learnspringjwt.models.Teacher;
import com.example.learnspringjwt.models.TeacherDAOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/admin/")
@RequiredArgsConstructor
public class AdminController {

    /**
     * For admin access where he can add and delete teachers, subjects and student
     */

    private final TeacherDAOService teacherDAOService;

    @GetMapping("/hi")
    public ResponseEntity<Object> retrieveAdmin() {
        return ResponseEntity.status(200).body("Hello from Admin");
    }

    @PostMapping("/create/teacher")
    public ResponseEntity<Object> addNewTeacher(@RequestBody Teacher request) {
        return teacherDAOService.createNewTeacher(request);
    }

}
