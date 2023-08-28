package com.workintech.course.entity;

import org.springframework.stereotype.Component;

@Component
public class MediumCourseGpa implements CourseGpa {


    @Override
    public final int gpa() {
        return 5;
    }
}
