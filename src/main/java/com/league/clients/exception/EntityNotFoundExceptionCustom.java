package com.league.clients.exception;

import jakarta.persistence.EntityNotFoundException;

public class EntityNotFoundExceptionCustom extends EntityNotFoundException {

    private static final String CLIENT_NOT_FOUND = "Client with id %d not found";

    EntityNotFoundExceptionCustom(String message) {
        super(message);
    }

    public static EntityNotFoundExceptionCustom of(Long id) {
        return new EntityNotFoundExceptionCustom(
                String.format(CLIENT_NOT_FOUND, id)
        );
    }
}
