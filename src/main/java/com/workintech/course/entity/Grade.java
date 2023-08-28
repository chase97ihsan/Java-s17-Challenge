package com.workintech.course.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Grade {
   private int coefficient;
   private String note;

    public Grade(int coefficient, String note) {
        this.coefficient = coefficient;
        this.note = note;
    }
}
