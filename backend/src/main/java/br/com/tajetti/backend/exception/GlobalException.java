package br.com.tajetti.backend.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalException {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(
        IllegalArgumentException e
    ) {
        return ResponseEntity
            .badRequest()
            .body(Map.of("error", e.getMessage()));
    } 


}
