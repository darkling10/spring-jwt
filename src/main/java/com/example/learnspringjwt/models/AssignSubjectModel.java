package com.example.learnspringjwt.models;

import com.example.learnspringjwt.teacher.Subjects;
import lombok.Data;

@Data
public class AssignSubjectModel {
    private String teacherID;
    private Subjects subjects;
}
