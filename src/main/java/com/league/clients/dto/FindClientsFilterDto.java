package com.league.clients.dto;

public record FindClientsFilterDto(
        Integer pageSize,
        Integer pageNum
) {
}
