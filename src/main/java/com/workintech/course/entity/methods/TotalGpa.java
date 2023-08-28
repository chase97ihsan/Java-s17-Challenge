package com.workintech.course.entity.methods;

import com.workintech.course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalGpa {

    private Course course;
    private double total_gpa;


}
