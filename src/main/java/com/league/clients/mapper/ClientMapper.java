package com.league.clients.mapper;

import com.league.clients.dto.ClientResponseDto;
import com.league.clients.dto.CreateClientDto;
import com.league.clients.entity.ClientAccountEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientAccountEntity clientDtoToEntity(CreateClientDto clientDto) {
        if (clientDto == null) return null;
        return ClientAccountEntity.builder()
                .fullName(clientDto.fullName())
                .gender(clientDto.gender())
                .build();
    }

    public ClientResponseDto toClientResponseDto(ClientAccountEntity clientAccountEntity) {
        if (clientAccountEntity == null) return null;
        return ClientResponseDto.builder()
                .id(clientAccountEntity.getId())
                .fullName(clientAccountEntity.getFullName())
                .gender(clientAccountEntity.getGender())
                .status(clientAccountEntity.getStatus())
                .createDttm(clientAccountEntity.getCreateDttm())
                .modifyDttm(clientAccountEntity.getModifyDttm())
                .build();

    }
}
