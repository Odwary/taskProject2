package com.league.clients.dto;

import com.league.clients.enums.ClientGender;
import com.league.clients.enums.ClientStatus;

public record FindClientsFilterDto(
        Long id,
        String fullName,
        ClientGender gender,
        ClientStatus status,
        Integer pageSize,
        Integer pageNum
) {
}
