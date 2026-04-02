package com.league.clients;

import com.league.clients.dto.ClientDto;
import com.league.clients.dto.CreateClientDto;
import com.league.clients.dto.FindClientsFilterDto;
import com.league.clients.dto.UpdateClientDto;
import com.league.clients.entity.ClientEntity;
import com.league.clients.enums.ClientStatus;
import com.league.clients.mapper.ClientMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
//todo переимновать package в exeption
//todo добавить lombok и писать через аннотация @RAG
//todo вынести логику логгирования в сервис
@Service
public class ClientsService {
    private final ClientsRepository repository;
    private final ClientMapper mapper;

    public ClientsService(ClientsRepository repository, ClientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ClientDto createClient(CreateClientDto request) {
        var clientEntity = mapper.clientDtoToEntity(request);
        clientEntity.setStatus(ClientStatus.ACTIVE);
        repository.save(clientEntity);
        return mapper.toDomain(clientEntity);
    }

    public List<ClientDto> findAllClientsByFilter(FindClientsFilterDto filter) {
        var pageSize = filter.pageSize() != null ? filter.pageSize() : 10;
        var pageNum = filter.pageNum() != null ? filter.pageNum() : 0;
        var pageable = Pageable.ofSize(pageSize).withPage(pageNum);
        List<ClientEntity> clientEntities = repository.findAllClientsByFilter(
                filter.id(),
                filter.fullName(),
                filter.gender(),
                filter.status(),
                pageable);
        return clientEntities.stream().map(mapper::toDomain).toList();
    }

    public ClientDto findById(Long id) {
        var clientEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                        String.format("no client with id %d", id)
                )
        );
        return mapper.toDomain(clientEntity);
    }

    public void deleteById(Long id) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException
                (String.format("no client with id %d", id)));
        repository.deleteById(id);
    }

    public void updateClient(Long id, UpdateClientDto client) {
        //todo убрать возможность менять дату создания и передавать в updateClient сразу dto не раскладывая на params
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException
                (String.format("no client with id %d", id)));
        repository.updateClient(
                id,
                client.fullName(),
                client.gender(),
                client.status(),
                client.createDttm(),
                OffsetDateTime.now()
        );
    }
}
