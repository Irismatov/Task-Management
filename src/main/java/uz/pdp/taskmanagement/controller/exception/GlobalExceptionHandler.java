package uz.pdp.taskmanagement.controller.exception;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        int start = e.getMessage().indexOf("Detail") + 8;
        int end = e.getMessage().indexOf(']', start);
        return ResponseEntity.status(500).body(e.getMessage().substring(start, end));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> handleBaseException(BaseException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getBindingResult().getFieldErrors().stream().collect(
                Collectors.toMap(
                        (FieldError::getField),
                        (FieldError::getDefaultMessage)
                )
        );
        return ResponseEntity.status(400).body(errors);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<String> handleExpiredJwtException(ExpiredTokenException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }



}
