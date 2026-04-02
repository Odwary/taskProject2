package com.league.clients.exception;

import jakarta.persistence.EntityNotFoundException;

public class EntityNotFoundExceptionCustom extends EntityNotFoundException {
    EntityNotFoundExceptionCustom(String message) {
        super(message);
    }

    public static EntityNotFoundExceptionCustom client(Long id) {
        return new EntityNotFoundExceptionCustom(
                String.format("Client with id %d not found", id)
        );
    }
}
