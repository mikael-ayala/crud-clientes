package com.devsuperior.crud.dto;

import com.devsuperior.crud.entities.Client;

import java.time.LocalDate;

public record ClientDTO(
        Long id,
        String name,
        String cpf,
        Double income,
        LocalDate birthDate,
        Integer children
        ) {
    public ClientDTO(Client client) {
        this(
            client.getId(),
            client.getName(),
            client.getCpf(),
            client.getIncome(),
            client.getBirthDate(),
            client.getChildren()
        );
    }
}
