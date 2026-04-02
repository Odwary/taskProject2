package com.league.clients.mapper;

import com.league.clients.dto.ClientResponseDto;
import com.league.clients.dto.CreateClientDto;
import com.league.clients.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {


    public ClientEntity clientDtoToEntity(CreateClientDto clientDto) {
        if (clientDto == null) return null;
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFullName(clientDto.fullName());
        clientEntity.setGender(clientDto.gender());
        return clientEntity;
    }

    public ClientResponseDto toDomain(ClientEntity clientEntity) {
        return new ClientResponseDto(clientEntity.getId(),
                clientEntity.getFullName(),
                clientEntity.getGender(),
                clientEntity.getStatus(),
                clientEntity.getCreateDttm(),
                clientEntity.getModifyDttm());
    }
}
