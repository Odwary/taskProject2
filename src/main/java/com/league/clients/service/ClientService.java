package com.league.clients.service;

import com.league.clients.dto.ClientResponseDto;
import com.league.clients.dto.CreateClientDto;
import com.league.clients.dto.FindClientsFilterDto;
import com.league.clients.dto.UpdateClientDto;
import com.league.clients.entity.ClientAccountEntity;
import com.league.clients.enums.ClientStatus;
import com.league.clients.exception.EntityNotFoundExceptionCustom;
import com.league.clients.mapper.ClientMapper;
import com.league.clients.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;

    public ClientResponseDto createClient(CreateClientDto request) {
        Instant instantTime = Instant.now();
        ClientAccountEntity clientAccountEntity = mapper.clientDtoToEntity(request);
        clientAccountEntity.setStatus(ClientStatus.ACTIVE);
        clientAccountEntity.setCreateDttm(instantTime);
        clientAccountEntity.setModifyDttm(instantTime);
        repository.save(clientAccountEntity);
        log.info("Client created: {}", request);
        return mapper.toClientResponseDto(clientAccountEntity);
    }

    public List<ClientResponseDto> findAllClientsByFilter(FindClientsFilterDto filter) {
        if (filter.pageNum() == null || filter.pageSize() == null) {
            log.info("findAll clients called");
            return repository.
                    findAll().
                    stream().
                    map(mapper::toClientResponseDto).
                    toList();
        } else {
            Integer pageSize = filter.pageSize();
            Integer pageNum = filter.pageNum();
            Pageable pageable = Pageable.
                    ofSize(pageSize).
                    withPage(pageNum);
            log.info("findAllClientsByFilter clients called");
            return repository.
                    findAllClientsByFilter(pageable).
                    stream().
                    map(mapper::toClientResponseDto).
                    toList();
        }
    }

    public ClientResponseDto findById(Long id) {
        ClientAccountEntity clientAccountEntity = repository.
                findById(id).orElseThrow(() ->
                        EntityNotFoundExceptionCustom.of(id)
                );
        log.info("Client with id: {} found", id);
        return mapper.toClientResponseDto(clientAccountEntity);
    }

    public void deleteById(Long id) {
        repository.findById(id)
                .orElseThrow(() -> EntityNotFoundExceptionCustom.of(id));
        repository.deleteById(id);
        log.info("Client with id: {} deleted", id);
    }

    public void updateClient(Long id, UpdateClientDto client) {
        repository.findById(id)
                .orElseThrow(() -> EntityNotFoundExceptionCustom.of(id));
        repository.updateClient(id,
                client,
                Instant.now()
        );
        log.info("Client with id: {} updated", id);
    }
}
