package com.league.clients.dto;

import com.league.clients.enums.ClientGender;
import com.league.clients.enums.ClientStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateClientDto(
        @NotNull
        String fullName,
        @NotNull
        ClientGender gender,
        @NotNull
        ClientStatus status
) {
}
