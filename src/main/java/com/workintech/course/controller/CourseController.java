package com.workintech.course.controller;

import com.workintech.course.entity.*;
import com.workintech.course.entity.methods.Gpa;
import com.workintech.course.entity.methods.TotalGpa;
import com.workintech.course.exceptions.CourseValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CourseController {

    private List<Course> courses;
    private CourseGpa lowCourseGpa;
    private CourseGpa mediumCourseGpa;
    private CourseGpa highCourseGpa;

    @PostConstruct
    public void init() {
        courses = new ArrayList<>();
    }

    @Autowired
    public CourseController(@Qualifier("lowCourseGpa") CourseGpa lowCourseGpa,
                            @Qualifier("mediumCourseGpa") CourseGpa mediumCourseGpa,
                            @Qualifier("highCourseGpa") CourseGpa highCourseGpa) {
        this.lowCourseGpa = lowCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.highCourseGpa = highCourseGpa;
    }

    @GetMapping("/")
    public List<Course> get() {
        return courses;
    }

    @GetMapping("/{name}")
    public Course get(@PathVariable String name) {
        CourseValidation.isNameNotExist(courses, name);
        Course course1 = new Course();
        for (Course a : courses) {
            if (a.getName().equals(name)) {
                course1 = a;
            }
        }
        return course1;
    }

    @PostMapping("/")
    public TotalGpa post(@RequestBody Course course) {
        CourseValidation.isNameExist(courses, course.getName());
        CourseValidation.isNotValidId(course.getId());
        CourseValidation.isNotValidCredit(course.getCredit());
        TotalGpa totalGpa = new TotalGpa();
        if (course.getCredit() <= 2) {
            totalGpa = new TotalGpa(course, new Gpa().gpaAll(course, lowCourseGpa));
        }
        if (course.getCredit() == 3) {
            totalGpa = new TotalGpa(course, new Gpa().gpaAll(course, mediumCourseGpa));
        }
        if (course.getCredit() == 4) {
            totalGpa = new TotalGpa(course, new Gpa().gpaAll(course, highCourseGpa));
        }
        courses.add(course);
        return totalGpa;
    }

    @PutMapping("/{id}")

    public TotalGpa put(@PathVariable int id, @RequestBody Course course) {
        CourseValidation.isNotValidId(course.getId());
        CourseValidation.isIdNotExist(courses, id);
        CourseValidation.isNotValidCredit(course.getCredit());
        TotalGpa totalGpa = new TotalGpa();
        if (course.getCredit() <= 2) {
            totalGpa = new TotalGpa(course, new Gpa().gpaAll(course, lowCourseGpa));
        }
        if (course.getCredit() == 3) {
            totalGpa = new TotalGpa(course, new Gpa().gpaAll(course, mediumCourseGpa));
        }
        if (course.getCredit() == 4) {
            totalGpa = new TotalGpa(course, new Gpa().gpaAll(course, highCourseGpa));
        }
        List<Course> course1 = courses.stream().filter(c -> c.getId() == id).collect(Collectors.toList());
        int index = courses.indexOf(course1.get(0));
        courses.set(index, course);
        return totalGpa;
    }

    @DeleteMapping("/{id}")
    public Course delete(@PathVariable int id) {
        CourseValidation.isNotValidId(id);
        CourseValidation.isIdNotExist(courses, id);
        List<Course> course1 = courses.stream().filter(c -> c.getId() == id).collect(Collectors.toList());
        int index = courses.indexOf(course1.get(0));
        Course course = courses.remove(index);
        return course;
    }
}
