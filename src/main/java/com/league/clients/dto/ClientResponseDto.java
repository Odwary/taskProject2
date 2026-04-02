package com.league.clients.dto;

import com.league.clients.enums.ClientGender;
import com.league.clients.enums.ClientStatus;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record ClientResponseDto(
        Long id,
        @NotNull
        String fullName,
        ClientGender gender,
        ClientStatus status,
        @NotNull
        Instant createDttm,
        @NotNull
        Instant modifyDttm) {
}
