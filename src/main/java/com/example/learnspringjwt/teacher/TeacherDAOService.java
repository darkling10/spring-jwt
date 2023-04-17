package com.example.learnspringjwt.teacher;

import com.example.learnspringjwt.admin.AdminController;
import com.example.learnspringjwt.customResponses.CustomResponse;
import com.example.learnspringjwt.repositories.SubjectJpaRepository;
import com.example.learnspringjwt.repositories.TeacherJpaRepository;
import com.example.learnspringjwt.repositories.UserRepository;
import com.example.learnspringjwt.user.Role;
import com.example.learnspringjwt.user.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherDAOService {

    private final TeacherJpaRepository teacherJpaRepository;

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final UserRepository userRepository;
    private final SubjectJpaRepository subjectJpaRepository;
    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<Object> createNewTeacher(Teacher request) {
        final String uuid = UUID.randomUUID().toString().replace("-", "");

//        Map<String, String> ans = new HashMap<>(request.getSubjects());
        List<Subjects> addSubjects = new ArrayList<>();

        if (request.getSubjects() != null) {
            addSubjects.addAll(request.getSubjects());
        }

        var newTeacherEntity = Teacher.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getFirstName())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .subjects(addSubjects)
                .build();


        var user = User.builder()
                .firstName((request.getFirstName()))
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf("TEACHER"))
                .build();

        try {
            userRepository.save(user);
            teacherJpaRepository.save(newTeacherEntity);
            if (request.getSubjects() != null) {
                for (Subjects subjects : addSubjects) {
                    subjectJpaRepository.save(subjects);
                }
            }

        } catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }


        return ResponseEntity.created(null).body(newTeacherEntity);
    }


    public List<Teacher> getAllTeachers() {
        return teacherJpaRepository.findAll();
    }

    public ResponseEntity<? extends Object> getTeacherByEmail(String email) {
        CustomResponse<Teacher> customResponse = new CustomResponse<>();
        Teacher teacherList = teacherJpaRepository.findOneByEmail(email);
        if (teacherList == null) {
            customResponse.setStatus("Not Found");
            customResponse.setMessage("The user with the given email is not found");
            return ResponseEntity.status(404).body(customResponse);
        }

        customResponse.setStatus("Success");
        customResponse.setMessage("Teacher found Successfully");
        customResponse.setData(teacherList);

        return ResponseEntity.ok().body(customResponse);
    }
}
