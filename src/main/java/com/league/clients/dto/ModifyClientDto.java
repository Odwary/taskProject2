package com.league.clients.dto;

import com.league.clients.enums.ClientGender;
import com.league.clients.enums.ClientStatus;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record ModifyClientDto(
        @NotNull
        String fullName,
        @NotNull
        ClientGender gender,
        @NotNull
        ClientStatus status,
        @NotNull
        OffsetDateTime createDttm
) {
}
