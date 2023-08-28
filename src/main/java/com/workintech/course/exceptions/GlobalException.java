package com.workintech.course.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler

    public ResponseEntity<CourseErrorResponse> handleException(CourseException courseException) {

        CourseErrorResponse courseErrorResponse = new CourseErrorResponse(courseException.getHttpStatus().value()
                , courseException.getMessage(), System.currentTimeMillis());
        log.error(courseException.getMessage());
        return new ResponseEntity<>(courseErrorResponse, courseException.getHttpStatus());
    }
    @ExceptionHandler
    public ResponseEntity<CourseErrorResponse> handleException(Exception courseException) {
        CourseErrorResponse courseErrorResponse = new CourseErrorResponse(HttpStatus.BAD_REQUEST.value()
                , courseException.getMessage(), System.currentTimeMillis());
        log.error(courseException.getMessage());
        return new ResponseEntity<>(courseErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
