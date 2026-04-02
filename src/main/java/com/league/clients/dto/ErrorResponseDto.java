package com.league.clients.dto;

import org.springframework.http.HttpStatusCode;

import java.time.Instant;

public record ErrorResponseDto(
        HttpStatusCode httpStatusCode,
        String detailedMessage,
        Instant dateTime
) {
}
