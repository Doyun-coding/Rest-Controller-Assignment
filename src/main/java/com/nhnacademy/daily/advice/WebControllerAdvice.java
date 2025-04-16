package com.nhnacademy.daily.advice;

import com.nhnacademy.daily.exception.DuplicateResourceException;
import com.nhnacademy.daily.exception.NotFoundResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler(NotFoundResourceException.class)
    public ResponseEntity<String> notFoundResourceException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("GET API 조회시 없는 리소스입니다.");
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<String> duplicateResourceException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("동일한 Resource 가 존재합니다.");
    }

}
