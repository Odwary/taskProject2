package com.league.clients.dto;

import com.league.clients.enums.ClientGender;
import jakarta.validation.constraints.NotNull;

public record CreateClientDto(
        @NotNull
        String fullName,
        @NotNull
        ClientGender gender) {
}
