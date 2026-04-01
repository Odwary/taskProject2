package com.league.clients.web;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> globalException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorResponseDto(
                                "500",
                                e.getMessage(),
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler({IllegalArgumentException.class,
            MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponseDto> illegalArgumentException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponseDto(
                                "404",
                                e.getMessage(),
                                LocalDateTime.now()
                        )
                );
    }

    @ExceptionHandler({NoSuchElementException.class,
            EntityNotFoundException.class})
    public ResponseEntity<ErrorResponseDto> noSuchElementException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDto(
                                "404",
                                e.getMessage(),
                                LocalDateTime.now()
                        )
                );
    }
}
