package com.league.clients;

import com.league.clients.dto.CreateClientDto;
import com.league.clients.dto.FindClientsFilterDto;
import com.league.clients.dto.ModifyClientDto;
import com.league.clients.enums.ClientGender;
import com.league.clients.enums.ClientStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {
    private final ClientsService clientsService;
    private final Logger logger = Logger.getLogger(ClientsController.class.getName());

    public ClientsController(ClientsService service) {
        this.clientsService = service;
    }

    @Tag(name = "POST", description = "POST methods for clients")
    @Operation(summary = "Создать клиента",
            description = "В ответе возвращается объект Client")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Клиент создан"),
            @ApiResponse(responseCode = "500", description = "Клиент не создан")})
    @PostMapping()
    public ResponseEntity<Client> createClient(@Parameter(description = "Объект CreateClientDto c полями fullName, gender",
            required = true) @Valid @RequestBody CreateClientDto request) {
        logger.info("createClient called");
        return ResponseEntity.status(HttpStatus.CREATED).body(clientsService.createClient(request));
    }

    @Tag(name = "GET", description = "GET methods for clients")
    @Operation(summary = "Найти всех клиентов по заданному фильтру",
            description = "Возвращает список объектов Client, найденных по фильтру")
    @ApiResponses(@ApiResponse(responseCode = "200",
            description = "Клиенты найдены"))
    @GetMapping
    public ResponseEntity<List<Client>> findAllByFilter(
            @Parameter(description = "Id Клиента", required = false) @RequestParam(required = false) Long id,
            @Parameter(description = "ФИО Клиента", required = false) @RequestParam(required = false) String fullName,
            @Parameter(description = "Пол Клиента", required = false) @RequestParam(required = false) ClientGender gender,
            @Parameter(description = "Статус Клиента", required = false) @RequestParam(required = false) ClientStatus status,
            @Parameter(description = "Размер страницы", required = false) @RequestParam(required = false) Integer pageSize,
            @Parameter(description = "Номер страницы", required = false) @RequestParam(required = false) Integer pageNum
    ) {
        var filter = new FindClientsFilterDto(
                id,
                fullName,
                gender,
                status,
                pageSize,
                pageNum);
        return ResponseEntity.ok().body(clientsService.findAllClientsByFilter(filter));
    }

    @Tag(name = "GET")
    @Operation(summary = "Найти клиента по его id",
            description = "Возвращает найденного по id клиента")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Клиент найден"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")})
    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@Parameter(description = "Id клиента", required = true)
                                           @PathVariable Long id) {
        logger.info("findById called");
        return ResponseEntity.ok().body(clientsService.findById(id));
    }

    @Tag(name = "PUT", description = "PUT methods for clients")
    @Operation(summary = "Обновить клиента")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Клиент обновлён"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")})
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@Parameter(description = "id клиента")@PathVariable Long id,
            @Parameter(description = "объект ModifyClientDto c полями fullName, gender, status, createDttm", required = true)
                                             @RequestBody ModifyClientDto request) {
        logger.info("updateClient called");
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
        logger.info("deleteById called");
        clientsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
