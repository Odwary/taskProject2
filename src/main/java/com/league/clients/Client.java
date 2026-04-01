package com.league.clients;

import com.league.clients.enums.ClientGender;
import com.league.clients.enums.ClientStatus;

import java.time.OffsetDateTime;

public record Client(
        Long id,
        String fullName,
        ClientGender gender,
        ClientStatus status,
        OffsetDateTime createDttm,
        OffsetDateTime modifyDttm) {
}
