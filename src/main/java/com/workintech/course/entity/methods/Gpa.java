package com.workintech.course.entity.methods;

import com.workintech.course.entity.Course;
import com.workintech.course.entity.CourseGpa;

public class Gpa {

    public static double gpaAll(Course course, CourseGpa courseGpa) {

        return course.getGrade().getCoefficient() * course.getCredit() * courseGpa.gpa();

    }
}
