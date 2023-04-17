package com.example.learnspringjwt.teacher;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity(name = "teacher_details")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String teacherId;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long phoneNumber;

//    @ElementCollection
//    @MapKeyColumn(name = "subjects_teach")
//    @CollectionTable(name = "subjects_teacher", joinColumns = @JoinColumn(name = "teacher_id"))
//    @Column(name = "subjects", length = 100)
//    @BatchSize(size = 20)
//    private Map<String, String> subjects;

    //    @OneToMany(targetEntity = Subjects.class)
////    @JoinColumn(name = "teacher_id")
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "teacherId", referencedColumnName = "teacherId"
    )
    private List<Subjects> subjects;
}
