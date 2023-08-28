package com.workintech.course.exceptions;

import com.workintech.course.entity.Course;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public class CourseValidation {


    public static void isNotValidId(int id) {
        if (id < 0) {
            throw new CourseException("This id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void isNotValidCredit(int credit) {
        if (credit < 0 || credit > 4) {
            throw new CourseException("This credit is not valid: " + credit, HttpStatus.BAD_REQUEST);
        }
    }

    public static void isIdNotExist(List<Course> courses, int id) {
        Optional<Course> optionalCourse = courses.stream().filter(c -> c.getId() == id).findFirst();
        if (!optionalCourse.isPresent()) {
            throw new CourseException("This id is not valid: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public static void isNameExist(List<Course> courses, String name) {
        Optional<Course> optionalCourse = courses.stream().filter(c -> c.getName().equals(name)).findFirst();
        if (optionalCourse.isPresent()) {
            throw new CourseException("There is  course that with given name: " + name, HttpStatus.BAD_REQUEST);
        }
    }
    public static void isNameNotExist(List<Course> courses, String name) {
        Optional<Course> optionalCourse = courses.stream().filter(c -> c.getName().equals(name)).findFirst();
        if (!optionalCourse.isPresent()) {
            throw new CourseException("There is not course that with given name: " + name, HttpStatus.BAD_REQUEST);
        }
    }
}
