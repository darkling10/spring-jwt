package com.example.learnspringjwt.repositories;

import com.example.learnspringjwt.teacher.Subjects;
import com.example.learnspringjwt.teacher.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@SpringBootTest
class SubjectJpaRepositoryTest {

    @Autowired
    public SubjectJpaRepository subjectJpaRepository;

    @Autowired
    public TeacherJpaRepository teacherJpaRepository;

    @Test
    public void saveSubjectWithTeacher() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");


        Subjects subject = Subjects.builder()
//                .courseId(uuid)
                .branch("MECH")
                .year("BE")
                .course("FOC")
                .build();

        Teacher teacher = Teacher.builder()
//                .teacherId(uuid + 100000)
                .email("abbas@gmail.com")
                .firstName("Abbas")
                .lastName("Pathan")
                .password("1234567")
                .subjects(List.of(subject))
                .build();
        teacherJpaRepository.save(teacher);
    }

    @Test
    public void retrieveAllSubject() {
        List<Subjects> subjects = subjectJpaRepository.findAll();
        for (Subjects subjects1 : subjects) {
            if (subjects1.getYear().equals("TE")) {
                System.out.println(subjects1.toString());
            }
            System.out.println(subjects1.getYear());
        }
//        System.out.println(subjects);
    }

    @Test
    public void retireveAllTeacher() {
        String email = "abbas@gmail.com";
        String ID = "2496b0cd1d6c487dae955fee19ba16fc100000";
        Optional<Teacher> teachers = teacherJpaRepository.findById(ID);
        Teacher teacherByEmail = teacherJpaRepository.findOneByEmail(email);
        System.out.println(email);
        System.out.println(teachers);
//        System.out.println(teacherByEmail);
    }

    @Test
    public void addSubject() {
        String email = "abbas@gmail.com";
        Teacher teacherByEmail = teacherJpaRepository.findOneByEmail(email);
        Subjects subject = Subjects.builder()
                .courseId("kwjefhiuertesuvrith")
                .course("DSBDA")
                .year("TE")
                .branch("COMP")
                .build();
        List<Subjects> assignSub = teacherByEmail.getSubjects();
        assignSub.add(subject);

        teacherByEmail.setSubjects(assignSub);
        teacherJpaRepository.save(teacherByEmail);

    }


}