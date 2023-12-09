package com.devsuperior.crud.dto;

import com.devsuperior.crud.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClientDTO(
        Long id,
        @NotBlank(message = "O campo não pode estar vazio")
        String name,
        String cpf,
        Double income,
        @PastOrPresent(message = "O campo não pode ser uma data futura")
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
