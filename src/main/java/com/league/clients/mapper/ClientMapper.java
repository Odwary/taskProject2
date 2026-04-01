package com.league.clients.mapper;

import com.league.clients.Client;
import com.league.clients.ClientEntity;
import com.league.clients.dto.CreateClientDto;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class ClientMapper {

    public ClientEntity toEntity(Client client) {
        return new ClientEntity(null,
                client.fullName(),
                client.gender(),
                client.status(),
                client.createDttm(),
                client.modifyDttm());
    }

    public ClientEntity clientDtoToEntity(CreateClientDto clientDto) {
        return new ClientEntity(null,
                clientDto.fullName(),
                clientDto.gender(),
                null,
                OffsetDateTime.now(),
                OffsetDateTime.now());
    }

    public Client toDomain(ClientEntity clientEntity) {
        return new Client(clientEntity.getId(),
                clientEntity.getFullName(),
                clientEntity.getGender(),
                clientEntity.getStatus(),
                clientEntity.getCreateDttm(),
                clientEntity.getModifyDttm());
    }
}
