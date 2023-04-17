package com.example.learnspringjwt.teacher;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "subjects_details")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Subjects {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    @Column(name = "courseId")
    private String courseId;

    private String course;
    private String year;
    private String branch;

//    @ManyToOne(
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(
//            name = "teacherId",
//            referencedColumnName = "teacherId"
//    )

}
