package com.league.clients.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        //todo заменить string на enum / http.status
        String message,
        String detailedMessage,
        LocalDateTime dateTime
) {
}
