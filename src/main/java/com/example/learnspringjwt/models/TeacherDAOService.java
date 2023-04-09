package com.example.learnspringjwt.models;

import com.example.learnspringjwt.admin.AdminController;
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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherDAOService {

    private final TeacherJpaRepository teacherJpaRepository;
    private final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<Object> createNewTeacher(Teacher request) {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        Map<String, String> ans = new HashMap<>(request.getSubjects());


        var newTeacherEntity = Teacher.builder()
                .id(uuid)
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getFirstName())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .subjects(ans)
                .build();


        for (Map.Entry<String, String> entry : ans.entrySet())
            logger.info("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());

        var user = User.builder()
                .id(uuid)
                .firstName((request.getFirstName()))
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf("TEACHER"))
                .build();

        try {
            teacherJpaRepository.save(newTeacherEntity);
            userRepository.save(user);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e);
        }


        return ResponseEntity.created(null).body(newTeacherEntity);
    }
}
