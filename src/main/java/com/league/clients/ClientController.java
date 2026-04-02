package com.league.clients;

import com.league.clients.dto.ClientResponseDto;
import com.league.clients.dto.CreateClientDto;
import com.league.clients.dto.FindClientsFilterDto;
import com.league.clients.dto.UpdateClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientsService;

    public ClientController(ClientService service) {
        this.clientsService = service;
    }

    @Tag(name = "POST", description = "POST methods for clients")
    @Operation(summary = "Создать клиента",
            description = "В ответе возвращается объект Client")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Клиент создан"),
            @ApiResponse(responseCode = "500", description = "Клиент не создан")})
    @PostMapping()
    public ResponseEntity<ClientResponseDto> createClient(@Parameter(description = "Объект CreateClientDto c полями fullName, gender",
            required = true) @Valid @RequestBody CreateClientDto request) {
        ClientResponseDto clients = clientsService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(clients);
    }

    @Tag(name = "GET", description = "GET methods for clients")
    @Operation(summary = "Найти всех клиентов по заданному фильтру",
            description = "Возвращает список объектов Client, найденных по фильтру")
    @ApiResponses(@ApiResponse(responseCode = "200",
            description = "Клиенты найдены"))
    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> findAllByFilter(
            @Parameter(description = "Размер страницы", required = false) @RequestParam(required = false) Integer pageSize,
            @Parameter(description = "Номер страницы", required = false) @RequestParam(required = false) Integer pageNum
    ) {
        var filter = new FindClientsFilterDto(
                pageSize,
                pageNum);
        List<ClientResponseDto> clients = clientsService.findAllClientsByFilter(filter);
        return ResponseEntity.ok().body(clients);
    }

    @Tag(name = "GET")
    @Operation(summary = "Найти клиента по его id",
            description = "Возвращает найденного по id клиента")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Клиент найден"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")})
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> findById(@Parameter(description = "Id клиента", required = true)
                                                      @PathVariable Long id) {
        ClientResponseDto client = clientsService.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @Tag(name = "PUT", description = "PUT methods for clients")
    @Operation(summary = "Обновить клиента")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Клиент обновлён"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")})
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@Parameter(description = "id клиента") @PathVariable Long id,
                                             @Parameter(description = "объект ModifyClientDto c полями fullName, gender, status, createDttm", required = true)
                                             @RequestBody UpdateClientDto request) {
        clientsService.updateClient(id, request);
        return ResponseEntity.ok().build();
    }

    @Tag(name = "DELETE", description = "DELETE methods for clients")
    @Operation(summary = "Удалить клиента по его id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Клиент удалён"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден и не удалён")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Parameter(description = "Id клиента", required = true)
                                           @PathVariable Long id) {
        clientsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
