package com.train.main.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<Map<String,Object>> notFound(EntityNotFoundException ex){
        return ResponseEntity.status(404).body(Map.of("error","Not Found"));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String,Object>> badReq(MethodArgumentNotValidException ex){
        var msgs = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField()+": "+fe.getDefaultMessage()).toList();
        return ResponseEntity.badRequest().body(Map.of("error","Validation", "details", msgs));
    }
}

