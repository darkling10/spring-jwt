package com.example.learnspringjwt.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.Map;

@Entity(name = "teacher_details")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Teacher {

    @Id
    private String id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long phoneNumber;

    @ElementCollection
    @MapKeyColumn(name = "subjects_teach")
    @CollectionTable(name = "subjects_teacher", joinColumns = @JoinColumn(name = "teacher_id"))
    @Column(name = "subjects", length = 100)
    @BatchSize(size = 20)
    private Map<String, String> subjects;
}
