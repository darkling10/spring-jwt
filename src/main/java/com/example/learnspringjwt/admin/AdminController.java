package com.example.learnspringjwt.admin;

import com.example.learnspringjwt.repositories.SubjectJpaRepository;
import com.example.learnspringjwt.repositories.TeacherJpaRepository;
import com.example.learnspringjwt.teacher.Teacher;
import com.example.learnspringjwt.teacher.TeacherDAOService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final SubjectJpaRepository subjectJpaRepository;

    @Autowired
    private final TeacherJpaRepository teacherJpaRepository;
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/hi")
    public ResponseEntity<Object> retrieveAdmin() {
        return ResponseEntity.status(200).body("Hello from Admin");
    }

    @PostMapping("/create/teacher")
    public ResponseEntity<Object> addNewTeacher(@RequestBody Teacher request) {

        logger.info("Teacher creation triggered :" + request);
        return teacherDAOService.createNewTeacher(request);
    }

    @PostMapping("/assign")
    public ResponseEntity<Object> assignSub(@RequestParam String email) {
        logger.info(email);
        Teacher teacher = teacherJpaRepository.findOneByEmail(email);


        return ResponseEntity.accepted().body(teacher);
    }

    @GetMapping("/getbyemail")
    public ResponseEntity<? extends Object> getTeacher(@RequestParam String email) {
        logger.info(email);
        return teacherDAOService.getTeacherByEmail(email);
    }

}
